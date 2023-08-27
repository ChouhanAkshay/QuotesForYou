package com.example.quotesforyou.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotesforyou.data.base.Status
import com.example.quotesforyou.data.model.response.Quote
import com.example.quotesforyou.domain.mappers.QuoteDataMapper
import com.example.quotesforyou.domain.model.QuoteData
import com.example.quotesforyou.domain.useCases.GetQuoteOfTheDayUseCase
import com.example.quotesforyou.ui.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quoteDataMapper: QuoteDataMapper,
    private val getQuoteOfTheDayUseCase: GetQuoteOfTheDayUseCase
): BaseViewModel() {
    private val getData = MutableLiveData<QuoteData>()
    val _getData : LiveData<QuoteData> = getData

    init{
        getData.postValue(null)
        getQuoteOfTheDay()
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
}