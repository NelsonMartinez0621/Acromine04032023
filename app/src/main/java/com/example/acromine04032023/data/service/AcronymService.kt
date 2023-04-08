package com.example.acromine04032023.data.service

import com.example.acromine04032023.data.models.AcronymResponse
import com.example.acromine04032023.data.models.AcronymResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {
    // http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=HMM

    @GET(SF_PATH)
    suspend fun getDataMeaning(
        @Query("sf") acronym: String
    ) : Response<List<AcronymResponseItem>>

    companion object {
        const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"
        private const val SF_PATH = "dictionary.py"
    }
}