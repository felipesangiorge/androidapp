package com.example.felipesangiorge.myapplication.services.myperfil

import com.example.felipesangiorge.myapplication.dao.web.MyPerfilWeb
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface MyPerfilServiceApiDef {
    @GET("userPerfil")
    fun myPerfilInfo(@Query("id") long:Long): Observable<MyPerfilWeb>
}

/*   @GET("/tasks/{task_id}")
    Task getTask(@Path("task_id") long taskId);*/