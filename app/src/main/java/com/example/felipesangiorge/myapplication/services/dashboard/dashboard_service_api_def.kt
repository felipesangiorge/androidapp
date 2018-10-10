package com.example.felipesangiorge.myapplication.services.dashboard
import com.example.felipesangiorge.myapplication.dao.web.SportListWeb
import retrofit2.http.GET
import rx.Observable

interface DashboardServiceApiDef {
    @GET("sportList")
    fun listSportList(): Observable<List<SportListWeb>>

}