package com.example.calculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator.data.entity.channel.Breed

@Database(entities = [Breed::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun breedsDao(): BreedsDao
}