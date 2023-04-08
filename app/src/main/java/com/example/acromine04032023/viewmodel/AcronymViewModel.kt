package com.example.acromine04032023.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acromine04032023.data.service.AcronymService
import com.example.acromine04032023.usecase.MeaningUseCase
import com.example.acromine04032023.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AcronymViewModel @Inject constructor(
    private val meaningUseCase: MeaningUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    var inputEmitter : ObservableEmitter<CharSequence>? = null
    var input : Observable<CharSequence> = Observable.create { emitter ->
        inputEmitter = emitter
    }

    private val _meaning: MutableLiveData<UIState> = MutableLiveData()
    val meaning: LiveData<UIState> get() = _meaning

    init {
        getMeaning(input.toString())
    }
    fun getMeaning(acronym: String) {
        viewModelScope.launch(ioDispatcher) {
            meaningUseCase.retrieveMeaning(
                acronym,
                loading = {_meaning.postValue(it)},
                success = {_meaning.postValue(it)},
                error = {_meaning.postValue(it)}
            )
        }
    }
}