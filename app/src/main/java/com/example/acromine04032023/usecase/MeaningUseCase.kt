package com.example.acromine04032023.usecase

import com.example.acromine04032023.data.service.AcronymRepo
import com.example.acromine04032023.utils.UIState
import javax.inject.Inject

class MeaningUseCase @Inject constructor(
    private val repo: AcronymRepo
        ) {

    suspend fun retrieveMeaning(shortForm: String,
                                success: (UIState.SUCCESS) ->Unit,
                                error: (UIState.ERROR) -> Unit,
                                loading: (UIState.LOADING) -> Unit
    ) {
        repo.getMeanings(shortForm).collect {
            when (it) {
                is UIState.LOADING -> {
                    loading.invoke(it)
                }
                is UIState.SUCCESS -> {
                    success.invoke(it)
                }
                is UIState.ERROR -> {
                    error.invoke(it)
                }
            }
        }
    }
}