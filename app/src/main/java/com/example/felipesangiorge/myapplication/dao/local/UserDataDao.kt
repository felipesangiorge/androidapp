package com.example.felipesangiorge.myapplication.dao.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.felipesangiorge.myapplication.database.entity.UserData

@Dao
interface UserDataDao{
    @Query("SELECT * from User")
    fun getAll(): List<UserData>

    @Insert(onConflict = REPLACE)
    fun insert(user: UserData)

    @Query("DELETE from User")
    fun deleteAll()
}