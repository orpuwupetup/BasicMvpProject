package com.example.orpuwupetup.basicmvpproject.data.source.repository

import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import com.example.orpuwupetup.basicmvpproject.data.source.MyObjectDataSource
import com.example.orpuwupetup.basicmvpproject.data.source.local.Local
import com.example.orpuwupetup.basicmvpproject.data.source.remote.Remote
import com.example.orpuwupetup.basicmvpproject.utils.verifiers.CacheExpirationManager
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyObjectRepository @Inject constructor(private val cacheExpirationManager: CacheExpirationManager, @Local private val myObjectLocalDataSource: MyObjectDataSource, @Remote private val myObjectRemoteDataSource: MyObjectDataSource): MyObjectDataSource {


    // TODO example repository for MyObject, including functions needed to fetch, cache and get products
    private var cachedObjects: MutableList<MyObject> = arrayListOf()
    private var cacheIsDirty: Boolean = false

    companion object {
        const val USER_OBJECT_LAST_REFRESH = "user_object_last_refresh"
    }

    override fun getMyObjects(): Single<List<MyObject>> {
        if (!cacheIsDirty && cachedObjects.isNotEmpty()) {
            return Single.just(cachedObjects)
        }

        if (!cacheIsDirty) {
            return myObjectLocalDataSource.getMyObjects()
        }

        return getObjectsFromRemoteDataSource()
    }

    private fun getObjectsFromRemoteDataSource(): Single<List<MyObject>> {
        return myObjectRemoteDataSource.getMyObjects()
            .doOnSuccess { objects ->
                refreshCache(objects)
                refreshLocalDataSource(objects)
                cacheExpirationManager.saveRefresh(USER_OBJECT_LAST_REFRESH)
                cacheIsDirty = false
            }.doOnError {
                cacheExpirationManager.saveRefresh(USER_OBJECT_LAST_REFRESH)
                cacheIsDirty = false
            }
    }

    private fun refreshLocalDataSource(objects: List<MyObject>) {
        myObjectLocalDataSource.refreshObjects()
        objects.forEach {
            saveMyObject(it)
        }
    }

    private fun refreshCache(objects: List<MyObject>) {
        cachedObjects.clear()

        objects.forEach {
            cachedObjects.add(it)
        }
    }

    override fun saveMyObject(myObject: MyObject) {
        myObjectLocalDataSource.saveMyObject(myObject)
    }

    override fun refreshObjects() {
        cacheIsDirty = true
    }
}