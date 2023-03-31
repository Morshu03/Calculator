package com.example.calculator.presentation.breed.model

sealed class BreedDetailUiState {
    data class Success(val imgUrl: String) : BreedDetailUiState()

    data class Error(val message: String): BreedDetailUiState()
}
