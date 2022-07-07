package com.demo.job.navigation

import com.demo.presentation.util.PHOTOS_BUNDLE_ITEM


sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Detail: Screen(route = "detail_screen/{$PHOTOS_BUNDLE_ITEM}"){
        fun createRoute(photoItem: String): String {
            return "detail_screen/$photoItem"
        }
    }
}