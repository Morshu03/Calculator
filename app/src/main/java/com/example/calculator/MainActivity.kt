package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.model.CalculatorViewModel
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.decisionButton.setOnClickListener {
            val aValue = binding.editTextSeniorCoefficient.text.toString()
            val bValue = binding.editTextAverageCoefficient.text.toString()
            val cValue = binding.editTextFreeMember.text.toString()
            if (aValue.isNotEmpty() && bValue.isNotEmpty() && cValue.isNotEmpty()) {
                calculate(
                    aValue = aValue.toDouble(),
                    bValue = bValue.toDouble(),
                    cValue = cValue.toDouble()
                )
            } else {
                Toast.makeText(
                    this@MainActivity, "Введены не все коофиценты", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @SuppressLint("SetTextI18n", "StringFormatMatches")
    private fun calculate(
        aValue: Double,
        bValue: Double,
        cValue: Double
    ) {
        val x1: Double
        val x2: Double

        val discriminant: Double = bValue * bValue - 4 * aValue * cValue

        if (discriminant >= 0) {
            x1 = (-bValue + sqrt(discriminant)) / (2 * aValue)
            x2 = (-bValue - sqrt(discriminant)) / (2 * aValue)

            binding.xOne.text = resources.getString(R.string.x1, x1)
            binding.xTwo.text = resources.getString(R.string.x2, x2) //вынестив ресурсы

        } else if (discriminant < 0) {
            binding.discriminantLessZero.text =
                resources.getString(R.string.discriminant, discriminant)//вынестив ресурсы
        }
    }
}