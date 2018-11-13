package com.example.orpuwupetup.basicmvpproject.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.orpuwupetup.basicmvpproject.data.converter.RoomConverters
import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import com.example.orpuwupetup.basicmvpproject.data.source.local.dao.MyObjectDao

@Database(

    // TODO add needed entities to database, and functions to create their DAOs
    entities = [(MyObject::class)],
    version = 1,
    exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class BasicDatabase: RoomDatabase() {

    abstract fun myObjectDao(): MyObjectDao
}