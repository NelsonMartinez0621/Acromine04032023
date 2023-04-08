package com.example.acromine04032023.data.service

import com.example.acromine04032023.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface AcronymRepo {
    fun getMeanings(acronym: String): Flow<UIState>
}

class AcronymRepoImpl @Inject constructor(
    private val service: AcronymService
) : AcronymRepo {

    override fun getMeanings(acronym: String): Flow<UIState>  = flow {
        emit(UIState.LOADING)

        try {
            val response = service.getDataMeaning(acronym)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it))
                } ?: throw Exception("response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        }catch (e:Exception) {
            emit(UIState.ERROR(e))
        }
    }

}