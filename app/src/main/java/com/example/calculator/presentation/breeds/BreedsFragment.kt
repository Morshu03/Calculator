package com.example.calculator.presentation.breeds

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculator.R
import com.example.calculator.databinding.FragmentBreedsBinding
import com.example.calculator.presentation.breed.BreedDetailFragment
import com.example.calculator.presentation.breeds.adapter.BreedsAdapter
import com.example.calculator.presentation.breeds.model.BreedsUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsFragment : Fragment(), RecyclerViewInterface {
    private val viewModel: BreedsViewModel by viewModels()
    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!
    private val adapter = BreedsAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.breedRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.breedRecyclerView.adapter = adapter
        viewModel.breedUiStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is BreedsUiState.Success -> {
                    adapter.setList(newBreedList = it.breeds)
                }
                is BreedsUiState.Error -> {
                    showToast(it.message)
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            activity, text, Toast.LENGTH_SHORT
        ).show()
    }
    override fun onItemClick(breed: String) {
        findNavController().navigate(R.id.action_breedsFragment_to_breedDetailFragment2, Bundle().apply {
            putString("breedsArguments", breed)
        })
    }
}
