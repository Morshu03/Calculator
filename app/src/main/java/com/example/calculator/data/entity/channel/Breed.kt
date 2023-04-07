package com.example.calculator.data.entity.channel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Breed(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String
)
