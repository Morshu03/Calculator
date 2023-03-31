package com.example.calculator.presentation.breed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.data.repository.DogRepository
import com.example.calculator.presentation.breed.model.BreedDetailUiState
import com.example.calculator.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(private val repository: DogRepository) :
    ViewModel() {
    val breedDetailUiStateLiveData: MutableLiveData<BreedDetailUiState> = MutableLiveData()

    init {
        fetchDogsByBreed(breed = "")
    }
    fun fetchDogsByBreed(breed: String) {
        viewModelScope.launch {
            when (val response = repository.getDogByBreed(breed)) {
                is RequestResult.Success -> {
                    breedDetailUiStateLiveData.postValue(
                        BreedDetailUiState.Success(
                            imgUrl = response.data?.message ?: ""
                        )
                    )
                }
                is RequestResult.Error -> {
                    breedDetailUiStateLiveData.postValue(
                        BreedDetailUiState.Error(
                            message = response.message ?: ""
                        )
                    )
                }
            }
        }
    }
}