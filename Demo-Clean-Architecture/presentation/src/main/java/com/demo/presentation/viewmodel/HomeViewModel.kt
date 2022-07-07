package com.demo.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.domain.extension.Result
import com.demo.domain.usecase.GetPhotosUseCase
import com.demo.presentation.mapper.PhotosUIMapper
import com.demo.presentation.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetPhotosUseCase
): ViewModel() {

    init {
        getPhotosList()
    }

    private val _state: MutableStateFlow<List<Photos>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<Photos>> = _state

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> = _errorState

    @VisibleForTesting
    internal fun getPhotosList() = viewModelScope.launch {
        useCase.invoke().collectLatest {
            when(it) {
                is Result.Loading -> {
                    _loadingState.value = true
                }
                is Result.Success -> {
                    _loadingState.value = false
                    _state.value = PhotosUIMapper().toUI(it.data)
                }
                is Result.Error -> {
                    _loadingState.value = false
                    _errorState.value = it.error
                }
            }
        }
    }

    fun refresh() = getPhotosList()
}