package com.example.calculator.presentation.calculator.model

sealed class CalculatorUiState {
    object Default : CalculatorUiState()
    data class Success(val x1: Double, val x2: Double) : CalculatorUiState()
    object Error : CalculatorUiState()
}