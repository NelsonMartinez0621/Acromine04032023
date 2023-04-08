package com.example.acromine04032023.utils

import com.example.acromine04032023.data.models.AcronymResponseItem

sealed class UIState {
    object LOADING: UIState()
    data class SUCCESS(val response: List<AcronymResponseItem>) :UIState()
    data class ERROR(val error: Exception): UIState()
}