package com.example.orpuwupetup.basicmvpproject.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import io.reactivex.Single

@Dao
interface MyObjectDao {

    // TODO add functions to get/set data from/in local database

    // example functions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyObject(myObject: MyObject)

    @Query("SELECT * FROM MyObject")
    fun getMyObjects(): Single<List<MyObject>>

    @Query("DELETE FROM MyObject")
    fun deleteAllObjects()
}