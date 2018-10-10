package com.example.felipesangiorge.myapplication.database.`class`

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.felipesangiorge.myapplication.dao.local.UserDataDao
import com.example.felipesangiorge.myapplication.database.entity.UserData

@Database(entities = arrayOf(UserData::class),version = 1)
abstract class UserDataBase:RoomDatabase(){
    abstract fun userDataDao():UserDataDao

    companion object {
        private var INSTANCE: UserDataBase? = null

        fun getInstance(context:Context):UserDataBase?{
            if(INSTANCE == null){
                synchronized(UserDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,UserDataBase::class.java,"user.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}