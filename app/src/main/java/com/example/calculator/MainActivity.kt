package com.example.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.model.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: CalculatorViewModel by viewModels()

        binding.decisionButton.setOnClickListener {
            val aValue = binding.editTextSeniorCoefficient.text.toString()
            val bValue = binding.editTextAverageCoefficient.text.toString()
            val cValue = binding.editTextFreeMember.text.toString()

            if (aValue.isNotEmpty() && bValue.isNotEmpty() && cValue.isNotEmpty()) {
                val result = viewModel.calculate(
                    aValue = aValue.toDouble(),
                    bValue = bValue.toDouble(),
                    cValue = cValue.toDouble()
                )
                if (result != null) {
                    binding.xOne.text = result.first.toString()
                    binding.xTwo.text = result.second.toString()
                } else {
                    Toast.makeText(
                        this@MainActivity, "Дискриминант равен 0", Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@MainActivity, "Введены не все коофиценты", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
