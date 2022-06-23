package com.demo.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.data.model.Photos

@Dao
interface DaoPhotos {
    @Query("SELECT * FROM album_table ORDER BY title ASC")
    suspend fun getPhotosList(): List<Photos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotosList(photos: List<Photos>)
}