package com.demo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class Photos(
    @ColumnInfo(name = "albumId")
    @PrimaryKey(autoGenerate = false)
    val albumId: Int,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String
)
