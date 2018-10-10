package com.example.felipesangiorge.myapplication.services.dashboard
import com.example.felipesangiorge.myapplication.`class`.SportItem
import com.example.felipesangiorge.myapplication.services.ServiceGenerator
import rx.Observable

class DashboardServiceApi(){

    fun loadSportList(): Observable<List<SportItem>> {
        return ServiceGenerator.createService(DashboardServiceApiDef::class.java).listSportList().map{
                sportList -> sportList.map {SportItem( it.title,
                                                        it.description,
                                                        it.sport,
                                                        it.category,
                                                        it.locale,
                                                        it.image,
                                                        it.date)}
        }}
    }
