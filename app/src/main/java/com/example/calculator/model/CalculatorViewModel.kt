package com.example.calculator.model

import androidx.lifecycle.ViewModel
import com.example.calculator.R
import kotlin.math.sqrt

class CalculatorViewModel() : ViewModel() {

    fun calculate(
        aValue: Double,
        bValue: Double,
        cValue: Double,
    ): Pair<Double, Double>? {
        val x1: Double
        val x2: Double

        val discriminant: Double = bValue * bValue - 4 * aValue * cValue

        if (discriminant >= 0) {
            x1 = (-bValue + sqrt(discriminant)) / (2 * aValue)
            x2 = (-bValue - sqrt(discriminant)) / (2 * aValue)
            return Pair(x1, x2)
        } else {
            return null
        }
    }
}
