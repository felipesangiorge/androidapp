package com.example.felipesangiorge.myapplication.database.entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class UserData(@PrimaryKey(autoGenerate = true) var id: Long?,
                @ColumnInfo(name = "name") var name: String,
                @ColumnInfo(name = "lastName") var lastName: String,
                @ColumnInfo(name = "cellPhone") var cellPhone: String,
                @ColumnInfo(name = "address") var address: String,
                @ColumnInfo(name = "city") var city: String,
                @ColumnInfo(name = "country") var country: String,
                @ColumnInfo(name = "photo") var photo: String,
                @ColumnInfo(name = "enabbleSuggestions") var enabbleSuggestions: Int,
                @ColumnInfo(name = "ennableLocationShare") var ennableLocationShare: Int) {
    constructor():this(null,"","","","","",
        "","",0,0)
}