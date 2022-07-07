package com.demo.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.demo.presentation.model.Photos
import com.demo.presentation.util.PHOTOS_BUNDLE_ITEM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    ):ViewModel() {

    val photos: Photos? = savedStateHandle.get(PHOTOS_BUNDLE_ITEM)

}