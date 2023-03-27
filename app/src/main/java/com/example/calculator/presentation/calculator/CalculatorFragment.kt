package com.example.calculator.presentation.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.calculator.databinding.FragmentCalculatorBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.calculator.presentation.calculator.model.UiState

@AndroidEntryPoint
class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("FragmentLiveDataObserve", "UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

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
        return binding.root
    }

    private fun showToast(text: String) {
        Toast.makeText(
            activity, text, Toast.LENGTH_SHORT
        ).show()
    }
}