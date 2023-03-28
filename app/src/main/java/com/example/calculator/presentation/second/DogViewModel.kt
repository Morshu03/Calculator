package com.example.calculator.presentation.second

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.data.repository.DogRepository
import com.example.calculator.presentation.calculator.model.DogUiState
import com.example.calculator.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val repository: DogRepository) : ViewModel() {

    val dogsUiStateLiveData: MutableLiveData<DogUiState> = MutableLiveData()


    fun fetchDogs() {
        viewModelScope.launch() {
            when (val response = repository.getRandomDog()) {
                is RequestResult.Success -> {
                    dogsUiStateLiveData.postValue(
                        DogUiState.Success(
                            imgUrl = response.data?.message ?: ""
                        )
                    )
                }
                is RequestResult.Error -> {
                    dogsUiStateLiveData.postValue(
                        DogUiState.Error(
                            massage = response.message ?: ""
                        )
                    )
                }
            }
        }
    }

    fun fetchDogsByBreed(breed: String) {
        viewModelScope.launch {
            when (val response = repository.getDogByBreed(breed)) {
                is RequestResult.Success -> {
                    dogsUiStateLiveData.postValue(
                        DogUiState.Success(
                            imgUrl = response.data?.message ?: ""
                        )
                    )
                }
                is RequestResult.Error -> {
                    dogsUiStateLiveData.postValue(
                        DogUiState.Error(
                            massage = response.message ?: ""
                        )
                    )
                }
            }
        }
    }
}