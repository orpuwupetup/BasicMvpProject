package com.example.orpuwupetup.basicmvpproject.activities

interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()
}