package com.example.orpuwupetup.basicmvpproject.data.source.remote

import com.example.orpuwupetup.basicmvpproject.api.BasicApiService
import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import com.example.orpuwupetup.basicmvpproject.data.source.MyObjectDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyObjectRemoteDataSource @Inject constructor(private val basicApiService: BasicApiService): MyObjectDataSource {

    // TODO remote implementation of data source

    override fun getMyObjects(): Single<List<MyObject>> {
        return basicApiService.getMyObjectsList()
    }

    override fun saveMyObject(myObject: MyObject) {
        // in this case, only local implementation of this function
        throw NotImplementedError()
    }

    override fun refreshObjects() {
        // in this case, only local implementation of this function
        throw NotImplementedError()
    }
}