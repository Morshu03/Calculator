package com.example.calculator.presentation.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.data.repository.HumorRepository
import com.example.calculator.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemeViewModel @Inject constructor(private val repository: HumorRepository) : ViewModel() {

    fun fetchMeme() {
        viewModelScope.launch {
            when(repository.getRandomMeme()) {
                is RequestResult.Success -> {

                }
                is RequestResult.Error -> TODO()
            }
        }
    }
}