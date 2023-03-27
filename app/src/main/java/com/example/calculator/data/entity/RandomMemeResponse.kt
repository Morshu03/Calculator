package com.example.calculator.data.entity

import com.google.gson.annotations.SerializedName

data class RandomMemeResponse(
    val id: Int?,
    @SerializedName("url")
    val imgUrl: String?,
    val type: String?
)
