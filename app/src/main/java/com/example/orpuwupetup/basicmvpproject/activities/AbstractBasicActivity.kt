package com.example.orpuwupetup.basicmvpproject.activities

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class AbstractBasicActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    abstract fun getLayout(): Int
}