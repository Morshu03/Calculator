package com.example.calculator.presentation.dog.model


sealed class DogUiState {
    data class Success(val imgUrl: String) : DogUiState()
    data class Error(val message: String) : DogUiState()
}