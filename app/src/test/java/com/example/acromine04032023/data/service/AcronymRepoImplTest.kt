package com.example.acromine04032023.data.service

import com.example.acromine04032023.data.models.AcronymResponseItem
import com.example.acromine04032023.utils.UIState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class AcronymRepoImplTest {
    private val service: AcronymService = mockk()
    private val repo = AcronymRepoImpl(service)

    @Test
    fun `getMeanings success`() = runBlocking {
        val acronym = "ROTFLOL"
        val expectedMeaning = listOf(AcronymResponseItem(sf = "Rolling on the Floor Laughing Out Loud"))
        val response = Response.success(expectedMeaning)
        coEvery { service.getDataMeaning(acronym) } returns response

        val result = repo.getMeanings(acronym).toList()

        assertEquals(listOf(UIState.LOADING, UIState.SUCCESS(expectedMeaning)), result)
    }

    @Test
    fun `getMeanings error`() = runBlocking {
        val acronym = "LOL"
        val expectedError = Exception("Error")
        coEvery { service.getDataMeaning(acronym) } throws expectedError

        val result = repo.getMeanings(acronym).toList()

        assertEquals(listOf(UIState.LOADING, UIState.ERROR(expectedError)), result)
    }
}