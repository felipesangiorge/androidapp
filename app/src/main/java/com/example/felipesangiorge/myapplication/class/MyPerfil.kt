package com.example.felipesangiorge.myapplication.`class`

data class MyPerfil(
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