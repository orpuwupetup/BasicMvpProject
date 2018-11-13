package com.example.orpuwupetup.basicmvpproject.activities.example

import android.os.Bundle
import com.example.orpuwupetup.basicmvpproject.R
import com.example.orpuwupetup.basicmvpproject.activities.AbstractBasicActivity
import com.example.orpuwupetup.basicmvpproject.utils.ui.animateHideNotification
import com.example.orpuwupetup.basicmvpproject.utils.ui.animateShowNotification
import com.example.orpuwupetup.basicmvpproject.utils.ui.setAnimatedClick
import kotlinx.android.synthetic.main.activity_example.*
import javax.inject.Inject

class ExampleActivity : AbstractBasicActivity(), ExampleContract.View {

    @Inject
    lateinit var presenter: ExampleContract.Presenter

    override fun getLayout(): Int {
        return R.layout.activity_example
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setViews()
    }

    private fun setViews() {
        cvShowNotification.setAnimatedClick({
            presenter.showNotificationButtonClicked()
        })

        ibCloseTopNotificationButton.setOnClickListener {
            clTopNotificationView.animateHideNotification()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        presenter.dropView()
        super.onPause()
    }

    override fun showTopNotification() {
        clTopNotificationView.animateShowNotification(2000)
    }
}
