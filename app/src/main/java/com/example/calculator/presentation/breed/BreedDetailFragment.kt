package com.example.calculator.presentation.breed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.calculator.R
import com.example.calculator.databinding.FragmentBreedDetailBinding
import com.example.calculator.presentation.breed.model.BreedDetailUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedDetailFragment : Fragment() {
    private val viewModel: BreedDetailViewModel by viewModels()
    private var _binding: FragmentBreedDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val breedArg: String = requireArguments().getString("breedsArguments").toString()
        binding.breedTextViewNavArgs.text = breedArg
        viewModel.fetchDogsByBreed(breed = breedArg)
        viewModel.breedDetailUiStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is BreedDetailUiState.Error -> {
                    showToast(it.message)
                }
                is BreedDetailUiState.Success -> {
                    Glide.with(this).load(it.imgUrl).into(binding.breedImageView)
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