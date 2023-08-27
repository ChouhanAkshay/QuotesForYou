package com.example.quotesforyou.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotesforyou.data.base.Status
import com.example.quotesforyou.data.model.request.GetQuotes
import com.example.quotesforyou.data.model.response.Quote
import com.example.quotesforyou.domain.mappers.QuoteDataMapper
import com.example.quotesforyou.domain.model.QuoteData
import com.example.quotesforyou.domain.useCases.GetQuoteOfTheDayUseCase
import com.example.quotesforyou.domain.useCases.GetQuotesUseCase
import com.example.quotesforyou.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quoteDataMapper: QuoteDataMapper,
    private val getQuoteOfTheDayUseCase: GetQuoteOfTheDayUseCase,
    private val getQuotesUseCase: GetQuotesUseCase
): BaseViewModel() {
    private val getData = MutableLiveData<QuoteData>()
    val _getData : LiveData<QuoteData> = getData

    val getQuotes = MutableLiveData<SnapshotStateList<QuoteData>>()
    val _getQuotes : LiveData<SnapshotStateList<QuoteData>> = getQuotes

    private val isLoading = MutableLiveData<Boolean>()
    val _isLoading : LiveData<Boolean> = isLoading

    private var hasMoreQuotes : Boolean? = false
    fun getHasMoreQuotes() = hasMoreQuotes

    private var page : Int = 1

    fun setPage(pageNo : Int) {
        page = pageNo
    }

    fun setIsLoading(isLoading : Boolean) {
        this.isLoading.postValue(isLoading)
    }

    init{
        getData.postValue(null)
        getQuotes.postValue(null)
        getQuoteOfTheDay()
        getQuotesList()
    }

    fun getQuoteOfTheDay() {
        fetchDataFromCoroutines(getQuoteOfTheDayUseCase) { response ->
            when(response?.status) {
                Status.SUCCESS -> {
                    response.data?.let { data ->
                        val newData = quoteDataMapper.map(data)
                        getData.postValue(newData)
                    }
                }
                else -> {
                    //todo show error view
                    response?.message?.let { data ->
                        Timber.e("newData : $data")
                    }
                }
            }
        }
    }

    private fun getQuotesList() {
        //todo remove loader if no more data
        val requestData = GetQuotes(
            page = page
        )
        fetchDataFromCoroutinesWithInput(getQuotesUseCase, input = requestData){ response ->
            isLoading.postValue(false)
            Timber.e("startRequest : 1")
            when(response?.status) {
                Status.SUCCESS -> {
                    response.data?.let { data ->
                        val listOfQuotes = mutableStateListOf<QuoteData>()
                        listOfQuotes.addAll(_getQuotes.value?.toList() ?: emptyList())

                        if(data.results.isNullOrEmpty()) {
                            hasMoreQuotes = false
                        } else {
                            hasMoreQuotes = true
                            data.results?.forEach { item ->
                                listOfQuotes.add(quoteDataMapper.map(item))
                            }
                        }

                        Timber.e("startRequest : 2 ${listOfQuotes?.size}")

                        getQuotes.postValue(listOfQuotes)
                    }
                }
                else -> {
                    hasMoreQuotes = false
                    response?.message?.let { data ->
                        Timber.e("newData : $data")
                    }
                }
            }
        }
    }

    fun getNextPageItems() {
        page += 1
        isLoading.postValue(true)
        getQuotesList()
    }
}