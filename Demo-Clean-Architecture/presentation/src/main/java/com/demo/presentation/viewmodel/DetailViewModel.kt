package com.demo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.domain.usecase.GetPhotoUseCase
import com.demo.presentation.mapper.PhotosUIMapper
import com.demo.presentation.model.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetPhotoUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<Photos> = MutableStateFlow(Photos())
    val state: StateFlow<Photos> = _state

    fun getPhoto(id: Int) = viewModelScope.launch {
        useCase.invoke(id).collectLatest {
            _state.value = PhotosUIMapper().toUI(it)
        }
    }
}