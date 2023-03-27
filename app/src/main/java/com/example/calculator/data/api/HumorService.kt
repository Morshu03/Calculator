package com.example.calculator.data.api

import com.example.calculator.data.entity.RandomMemeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HumorService {

    @GET("/memes/random")
    suspend fun getRandomMeme(@Query ("api-key") apiKey: String): Response<RandomMemeResponse>
}