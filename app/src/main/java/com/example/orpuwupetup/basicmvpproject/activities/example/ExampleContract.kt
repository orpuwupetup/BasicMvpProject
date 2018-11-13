package com.example.orpuwupetup.basicmvpproject.activities.example

import com.example.orpuwupetup.basicmvpproject.activities.BasePresenter
import com.example.orpuwupetup.basicmvpproject.activities.BaseView

interface ExampleContract {

    // TODO add here View methods that Presenter will want to use
    interface View: BaseView<Presenter>

    // TODO add here Presenter methods that View will want to use
    interface Presenter: BasePresenter<View>
}