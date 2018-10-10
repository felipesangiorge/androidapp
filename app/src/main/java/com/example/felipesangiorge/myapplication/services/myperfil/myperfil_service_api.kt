package com.example.felipesangiorge.myapplication.services.myperfil

import com.example.felipesangiorge.myapplication.`class`.MyPerfil
import com.example.felipesangiorge.myapplication.services.ServiceGenerator
import rx.Observable

class MyPerfilServiceApi() {
    var id:Long = 58

    fun loadMyPerfil(): Observable<MyPerfil> {
        return ServiceGenerator.createService(MyPerfilServiceApiDef::class.java).myPerfilInfo(id)
            .map { myPerfil ->
                    MyPerfil(
                        myPerfil.name,
                        myPerfil.lastName,
                        myPerfil.cellPhone,
                        myPerfil.address,
                        myPerfil.city,
                        myPerfil.country,
                        myPerfil.photo,
                        myPerfil.enabbleSuggestions,
                        myPerfil.enabbleLocationShare,
                        myPerfil.sportCategory
                    )
                }
            }
    }