package com.example.calculator.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calculator.data.entity.channel.Breed

@Dao
interface BreedsDao {
    @Query("SELECT * FROM breed")
    fun getBreeds(): List<Breed>

    @Insert
    fun insertBreeds(breed: List<Breed>)
}