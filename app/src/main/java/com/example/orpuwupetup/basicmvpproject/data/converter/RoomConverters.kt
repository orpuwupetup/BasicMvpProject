package com.example.orpuwupetup.basicmvpproject.data.converter

import android.arch.persistence.room.TypeConverter
import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object RoomConverters {
    
    // TODO Put needed converters here
    

    // example converter from Json to List<MyObject>:

    @TypeConverter
    @JvmStatic
    fun fromStringToMyObjectList(json: String?): List<MyObject>? {
        val moshi = Moshi.Builder().build()
        if(json != null){
            val type = Types.newParameterizedType(List::class.java, MyObject::class.java)
            return moshi.adapter<List<MyObject>>(type).fromJson(json)
        }
        return arrayListOf()
    }


    // example converter from List<MyObject> to Json:

    @TypeConverter
    @JvmStatic
    fun fromMyObjectsListToString(myObjects: List<MyObject>?): String {
        val moshi = Moshi.Builder().build()
        val listMyData = Types.newParameterizedType(List::class.java, MyObject::class.java)
        return moshi.adapter<List<MyObject>>(listMyData).toJson(myObjects)
    }
}