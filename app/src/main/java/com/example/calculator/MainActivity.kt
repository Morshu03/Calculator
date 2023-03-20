package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.model.UiState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: CalculatorViewModel by viewModels()

        viewModel.uiStateLiveData.observe(this) {
            when (it) {
                UiState.Default -> {
                    binding.xOne.visibility = View.GONE
                    binding.xTwo.visibility = View.GONE
                }
                UiState.Error -> {
                    binding.xOne.visibility = View.GONE
                    binding.xTwo.visibility = View.GONE
                    showToast("Дискриминант равен 0")
                }
                is UiState.Success -> {
                    binding.xOne.visibility = View.VISIBLE
                    binding.xTwo.visibility = View.VISIBLE
                    binding.xOne.text = it.x1.toString()
                    binding.xTwo.text = it.x2.toString()
                }
            }
        }

        binding.decisionButton.setOnClickListener {
            val aValue = binding.editTextSeniorCoefficient.text.toString()
            val bValue = binding.editTextAverageCoefficient.text.toString()
            val cValue = binding.editTextFreeMember.text.toString()
            if (aValue.isNotEmpty() && bValue.isNotEmpty() && cValue.isNotEmpty()) {
                viewModel.calculate(
                    aValue = aValue.toDouble(),
                    bValue = bValue.toDouble(),
                    cValue = cValue.toDouble()
                )
            } else {
                showToast("Введены не все коофиценты")
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            this@MainActivity, text, Toast.LENGTH_SHORT
        ).show()
    }
}
