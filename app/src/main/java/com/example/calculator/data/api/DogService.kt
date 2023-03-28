package com.example.calculator.data.api

import com.example.calculator.data.entity.RandomDogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {
    @GET("/api/breeds/image/random")
    suspend fun getRandomDog(): Response<RandomDogResponse>
    @GET("api/breed/{breed}/images/random")
    suspend fun getDogByBreed(@Path("breed") breed: String): Response<RandomDogResponse>
}