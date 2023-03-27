package com.example.calculator.data.api

import com.example.calculator.data.entity.RandomDogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogService {

    @GET("/api/breeds/image/random")
    suspend fun getRandomDog(
       // @Query ("api-key") apiKey: String
    ): Response<RandomDogResponse>
}