package com.example.calculator.data.repository

import com.example.calculator.data.api.DogService
import com.example.calculator.data.entity.RandomDogResponse
import com.example.calculator.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(private val dogService: DogService) {

    suspend fun getRandomDog(): RequestResult<RandomDogResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = dogService.getRandomDog()
                if (result.isSuccessful) {
                    result.body()?.let {
                        return@withContext RequestResult.Success(it)
                    }
                }
                return@withContext RequestResult.Error(result.message())
            } catch (e: java.lang.Exception) {
                return@withContext RequestResult.Error(e.message)
            }
        }
    }

    companion object {
        private const val API_KEY = "30a7cb9b1d0a4e2fb6a615a1ef43d3b2"
    }


}