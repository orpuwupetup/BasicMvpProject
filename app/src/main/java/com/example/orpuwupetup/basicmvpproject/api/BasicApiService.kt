package com.example.orpuwupetup.basicmvpproject.api

import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import io.reactivex.Single
import retrofit2.http.GET

interface BasicApiService {

    // TODO Add HTTP requests here

    // example request
    @GET("write/endpoint/path/here")
    fun getMyObjectsList() : Single<List<MyObject>>
}