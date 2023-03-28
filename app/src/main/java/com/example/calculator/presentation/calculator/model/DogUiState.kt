package com.example.calculator.presentation.calculator.model


sealed class DogUiState {
    data class Success(val imgUrl: String) : DogUiState()
    data class Error(val massage: String) : DogUiState()
}