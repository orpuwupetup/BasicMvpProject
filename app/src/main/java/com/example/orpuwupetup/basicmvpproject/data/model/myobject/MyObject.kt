package com.example.orpuwupetup.basicmvpproject.data.model.myobject

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



@Entity
open class MyObject (

    // TODO MyObject model (fields represent columns in database or parts of data received from API)
    @PrimaryKey val name: String
)