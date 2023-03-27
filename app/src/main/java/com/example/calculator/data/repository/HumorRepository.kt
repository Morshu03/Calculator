package com.example.calculator.data.repository

import com.example.calculator.data.api.HumorService
import com.example.calculator.data.entity.RandomMemeResponse
import com.example.calculator.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HumorRepository @Inject constructor(private val humorService: HumorService) {

    suspend fun getRandomMeme(): RequestResult<RandomMemeResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = humorService.getRandomMeme(API_KEY)
                if (result.isSuccessful) {
                    result.body()?.let {
                        RequestResult.Success(it)
                    }
                }
                RequestResult.Error(result.message())
            } catch (e: java.lang.Exception) {
                RequestResult.Error(e.message)
            }
        }
    }

    companion object {
        private const val API_KEY = "30a7cb9b1d0a4e2fb6a615a1ef43d3b2"
    }


}