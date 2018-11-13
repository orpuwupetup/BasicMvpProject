package com.example.orpuwupetup.basicmvpproject.data.source

import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import io.reactivex.Single

interface MyObjectDataSource {

    // TODO interface with functions representing what we want to do with our objects (ex.: save it to database, get it from data base etc.)

    // example functions
    fun getMyObjects(): Single<List<MyObject>>
    fun saveMyObject(myObject: MyObject)
    fun refreshObjects()
}