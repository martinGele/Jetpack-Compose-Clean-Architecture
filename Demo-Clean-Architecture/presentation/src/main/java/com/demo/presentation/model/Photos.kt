package com.demo.presentation.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photos(
    val albumId: Int = 0,
    val id: Int = 0,
    val thumbnailUrl: String = "",
    val title: String = "",
    val url: String = ""
) : Parcelable


class NavTypePhotos : NavType<Photos>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Photos? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Photos {
        return Gson().fromJson(value, Photos::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Photos) {
        bundle.putParcelable(key, value)
    }

}