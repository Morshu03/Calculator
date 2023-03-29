package com.example.calculator.presentation.breeds

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculator.databinding.FragmentBreedsBinding
import com.example.calculator.presentation.breeds.adapter.BreedsAdapter
import com.example.calculator.presentation.breeds.model.BreedsUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsFragment : Fragment() {
    private val viewModel: BreedsViewModel by viewModels()
    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!
    private val adapter = BreedsAdapter()


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
        viewModel.breedUiStateLiveData.observe(viewLifecycleOwner){
            when(it){
                is BreedsUiState.Success -> {
                    adapter.setList(newBreedList = it.breeds)
                }
                is BreedsUiState.Error -> {

                }
            }
        }
    }
}
