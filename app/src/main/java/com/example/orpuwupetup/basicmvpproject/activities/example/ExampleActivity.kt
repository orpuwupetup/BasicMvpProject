package com.example.orpuwupetup.basicmvpproject.activities.example

import com.example.orpuwupetup.basicmvpproject.R
import com.example.orpuwupetup.basicmvpproject.activities.AbstractBasicActivity
import javax.inject.Inject

class ExampleActivity : AbstractBasicActivity(), ExampleContract.View {

    @Inject
    lateinit var presenter: ExampleContract.Presenter

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()
        super.onPause()
    }
}
