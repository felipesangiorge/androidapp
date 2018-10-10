package com.example.felipesangiorge.myapplication.dao.web

import com.example.felipesangiorge.myapplication.`class`.SportCategoryItem

data class MyPerfilWeb(
    val name:String,
    val lastName:String,
    val cellPhone:String,
    val address:String,
    val city:String,
    val country:String,
    val photo:String,
    val enabbleSuggestions:Int,
    val enabbleLocationShare:Int,
    val sportCategory:List<SportCategoryItem>
){}