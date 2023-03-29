package com.example.calculator.data.repository

import com.example.calculator.data.api.DogService
import com.example.calculator.data.entity.BreedsResponse
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

    suspend fun getDogByBreed(breed: String): RequestResult<RandomDogResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val breedsResult = dogService.getDogByBreed(breed)
                if (breedsResult.isSuccessful) {
                    breedsResult.body().let {
                        return@withContext RequestResult.Success(it)
                    }
                }
                return@withContext RequestResult.Error(breedsResult.message())
            } catch (e: java.lang.Exception) {
                return@withContext RequestResult.Error(e.message)
            }
        }
    }

    suspend fun getSetOfBreeds(): RequestResult<BreedsResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val breedsResult = dogService.getSetOfBreeds()
                if (breedsResult.isSuccessful) {
                    breedsResult.body().let {
                        return@withContext RequestResult.Success(it)
                    }
                }
                return@withContext RequestResult.Error(breedsResult.message())
            } catch (e: java.lang.Exception) {
                return@withContext RequestResult.Error(e.message)
            }
        }
    }

}