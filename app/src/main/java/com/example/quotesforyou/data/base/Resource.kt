package com.example.quotesforyou.data.base

data class Resource<out T>(val status  : Status, val data : T?, val message : String?) {
    companion object{
        /**
         * get success of failure result in form of resource
         * */
        fun <T> success(data : T?) : Resource<T>{
            return Resource(Status.SUCCESS,data,null)
        }

        fun <T> error(error : Error = Error(), data : T? = null)  : Resource<T> {
            return Resource(Status.ERROR, data, error.message)
        }
    }
}