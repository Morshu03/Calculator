package com.example.calculator.presentation.breeds.model

sealed class BreedsUiState {

    data class Success(val breeds: Set<String>): BreedsUiState()

    data class Error(val message: String): BreedsUiState()
}
