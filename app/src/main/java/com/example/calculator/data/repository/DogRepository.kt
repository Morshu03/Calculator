package com.example.calculator.data.repository

import android.widget.Toast
import com.example.calculator.data.db.BreedsDao
import com.example.calculator.data.entity.api.DogService
import com.example.calculator.data.entity.BreedsResponse
import com.example.calculator.data.entity.RandomDogResponse
import com.example.calculator.data.entity.channel.Breed
import com.example.calculator.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DogRepository @Inject constructor(
    private val dogService: DogService,
    private val breedsDao: BreedsDao
) {

    suspend fun getRandomDog(): RequestResult<RandomDogResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val breedsList = breedsDao.getBreeds()
                val set = breedsList.map { it }.toSet()

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
                val jObjError = JSONObject(breedsResult.errorBody()?.string().toString())
                val message = jObjError.getString("message")
                return@withContext RequestResult.Error(message)
            } catch (e: java.lang.Exception) {
                return@withContext RequestResult.Error(e.message)
            }
        }
    }

    suspend fun getSetOfBreeds(): RequestResult<Set<String>?> {
        return withContext(Dispatchers.IO) {
            val breedList = breedsDao.getBreeds()
            if (breedList.isEmpty()) {
                try {
                    val breedsResult = dogService.getSetOfBreeds()
                    if (breedsResult.isSuccessful) {
                        breedsResult.body()?.message?.keys.let {
                            if (it != null) {
                                breedsDao.insertBreeds(it.map { Breed(name = it) })
                                return@withContext RequestResult.Success(it)
                            }
                        }
                    }
                    return@withContext RequestResult.Error(breedsResult.message())
                } catch (e: java.lang.Exception) {
                    return@withContext RequestResult.Error(e.message)
                }
            } else {
                return@withContext RequestResult.Success(breedList.map { it.name }.toSet())
            }
        }
    }

}