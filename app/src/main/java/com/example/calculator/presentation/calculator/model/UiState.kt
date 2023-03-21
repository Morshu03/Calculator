package com.example.calculator.presentation.calculator.model

sealed class UiState {
    object Default : UiState()
    data class Success(val x1: Double, val x2: Double) : UiState()
    object Error : UiState()
}
