package com.example.calculator.presentation.breeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.data.repository.DogRepository
import com.example.calculator.presentation.breeds.model.BreedsUiState
import com.example.calculator.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(private val repository: DogRepository): ViewModel() {

    val breedUiStateLiveData: MutableLiveData<BreedsUiState> = MutableLiveData()

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            when (val response = repository.getSetOfBreeds()) {
                is RequestResult.Success -> {
                    breedUiStateLiveData.postValue(
                        response.data?.message?.let {
                            BreedsUiState.Success(
                                breeds = it.keys
                            )
                        }
                    )
                }
                is RequestResult.Error -> {
                    breedUiStateLiveData.postValue(
                        BreedsUiState.Error(
                            message = response.message ?: ""
                        )
                    )
                }
            }
        }
    }
}