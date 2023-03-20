package com.example.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.sqrt

class CalculatorViewModel : ViewModel() {

    val uiStateLiveData: MutableLiveData<UiState> = MutableLiveData(UiState.Default)

    fun calculate(
        aValue: Double,
        bValue: Double,
        cValue: Double,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val discriminant = bValue * bValue - 4 * aValue * cValue
                if (discriminant > 0) {
                    val x1 = (-bValue + sqrt(discriminant)) / (2 * aValue)
                    val x2 = (-bValue - sqrt(discriminant)) / (2 * aValue)
                    uiStateLiveData.postValue(UiState.Success(x1, x2))
                } else {
                    uiStateLiveData.postValue(UiState.Error)
                }
            }
        }
    }
}
