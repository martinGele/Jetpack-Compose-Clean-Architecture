package com.demo.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.domain.usecase.GetPhotoUseCase
import com.demo.presentation.mapper.PhotosUIMapper
import com.demo.presentation.model.Photos
import com.demo.presentation.util.PHOTOS_BUNDLE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: GetPhotoUseCase
) : ViewModel() {

    val photos: Photos? = savedStateHandle.get(PHOTOS_BUNDLE_ITEM)

    private val _state: MutableStateFlow<Photos> = MutableStateFlow(Photos())
    val state: StateFlow<Photos> = _state

    fun getPhoto(id: Int) = viewModelScope.launch {
        useCase.invoke(id).collectLatest {
            _state.value = PhotosUIMapper().toUI(it)
        }
    }
}