package com.example.quotesforyou.ui.base.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.example.quotesforyou.data.base.ApiResponse
import com.example.quotesforyou.data.base.BaseUseCaseUnWrapped
import com.example.quotesforyou.data.base.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

abstract class BaseViewModel : ViewModel() {

    //create job
    private val jobDelegate = lazy { SupervisorJob() }
    private val job by jobDelegate

    //create scope
    protected val ioScope by lazy {
        CoroutineScope(job + Dispatchers.IO)
    }

    protected inline fun <O : ApiResponse> fetchDataFromCoroutines(
        useCase : BaseUseCaseUnWrapped<O>,
        crossinline response : (res : Resource<O>?) -> Unit
    ) {
        ioScope.launch {
            (useCase.processRequest()).collect {
                response(it)
            }
        }
    }

    @CallSuper
    override fun onCleared() {
        if (jobDelegate.isInitialized()) {
            job.cancel()
        }
        super.onCleared()
    }
}