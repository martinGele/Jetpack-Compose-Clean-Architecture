package com.demo.domain.entity

class MockPhotosList {
    fun getPhotosList() = listOf(
        Photos(
            albumId = 1,
            id = 1,
            title = "accusamus beatae ad facilis cum similique qui sunt",
            url = "https://via.placeholder.com/600/92c952",
            thumbnailUrl = "https://via.placeholder.com/150/92c952"
        )
    )

    fun getPhotos() =
        Photos(
            albumId = 2,
            id = 2,
            title = "accusamus beatae ad facilis cum similique qui sunt",
            url = "https://via.placeholder.com/600/92c952",
            thumbnailUrl = "https://via.placeholder.com/150/92c952"
        )

}