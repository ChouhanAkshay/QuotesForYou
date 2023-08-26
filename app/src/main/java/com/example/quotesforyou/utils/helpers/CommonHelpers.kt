package com.example.quotesforyou.utils.helpers

import com.example.quotesforyou.data.base.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> createSuccess(response : T) : Resource<T> = Resource.success(response)

fun <T> createErrorWithResponse(response : Response<T>) : Resource<T> {
    return try {
        val error : Error = Gson().fromJson(
            response.errorBody()?.string(),
            java.lang.Error::class.java
        )
        Resource.error(error)
    } catch (e : Exception) {
        createError(e)
    }
}

private fun <T> createError(t : Throwable?) : Resource<T> {
    return Resource.error(Error())
}


internal inline fun <T> processData(response : () -> Response<T>) : Flow<Resource<T>> {
    return try {
        response().run {
            if(this.isSuccessful) {
                body()?.let{
                    flow { emit(createSuccess(it)) }
                } ?: run {
                    flow { emit( createErrorWithResponse(this@run))}
                }
            } else {
                flow { emit( createErrorWithResponse(this@run))}
            }
        }
    } catch (e : Exception) {
        return flow { emit (createError(e.cause))}
    }
}