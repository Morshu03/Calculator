package com.example.calculator.presentation.dog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.calculator.R
import com.example.calculator.databinding.FragmentDogBinding
import com.example.calculator.presentation.dog.model.DogUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogFragment : Fragment() {

    val viewModel: DogViewModel by viewModels()
    private var _binding: FragmentDogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getButton = view.findViewById<Button>(R.id.getButton)

        getButton.setOnClickListener {
            val breed = binding.textFieldDogsBreed.text.toString()
            if (breed.isEmpty()) {
                viewModel.fetchDogs()
            } else {
                viewModel.fetchDogsByBreed(breed = breed)
            }
        }

        viewModel.dogsUiStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DogUiState.Error -> {
                    showToast(it.message)
                }
                is DogUiState.Success -> {
                    Glide.with(this).load(it.imgUrl).into(binding.imageView)
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            activity, text, Toast.LENGTH_SHORT
        ).show()
    }

}