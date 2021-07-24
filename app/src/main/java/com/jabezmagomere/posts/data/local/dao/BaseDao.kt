package com.jabezmagomere.posts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BaseDao<T> {

    @Insert
    suspend fun insert(t: T)

    @Insert
    suspend fun insert(t: List<T>)

    @Insert
    suspend fun update(t: T)

    @Insert
    suspend fun delete(t: T)
}