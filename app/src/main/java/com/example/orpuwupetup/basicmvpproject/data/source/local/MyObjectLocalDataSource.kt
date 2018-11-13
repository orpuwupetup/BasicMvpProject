package com.example.orpuwupetup.basicmvpproject.data.source.local

import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import com.example.orpuwupetup.basicmvpproject.data.source.MyObjectDataSource
import com.example.orpuwupetup.basicmvpproject.data.source.local.dao.MyObjectDao
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class MyObjectLocalDataSource(private val dao: MyObjectDao): MyObjectDataSource {

    // TODO local implementation of data source
    override fun getMyObjects(): Single<List<MyObject>> {
        return dao.getMyObjects()
    }

    override fun saveMyObject(myObject: MyObject) {
        dao.insertMyObject(myObject)
    }

    override fun refreshObjects() {
        dao.deleteAllObjects()
    }
}