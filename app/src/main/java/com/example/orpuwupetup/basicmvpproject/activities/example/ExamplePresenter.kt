package com.example.orpuwupetup.basicmvpproject.activities.example

import android.util.Log
import com.example.orpuwupetup.basicmvpproject.data.model.myobject.MyObject
import com.example.orpuwupetup.basicmvpproject.data.source.repository.MyObjectRepository
import com.example.orpuwupetup.basicmvpproject.utils.verifiers.CacheExpirationManager
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExamplePresenter @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val cacheExpirationManager: CacheExpirationManager,
    private val myObjectRepository: MyObjectRepository
) : ExampleContract.Presenter {

    private var view: ExampleContract.View? = null
    private var firstLoad = true

    override fun takeView(view: ExampleContract.View) {
        this.view = view

        makeRxJavaCallWithReturnValue()
        makeRxJavaCallWithoutReturnValue()
    }

    private fun makeRxJavaCallWithoutReturnValue() {
        val newMyObject: MyObject = object : MyObject("object name"){}

        // example RxJava call without returning value
        val saveObjectDisposable = Completable.create {
            myObjectRepository.saveMyObject(newMyObject)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        compositeDisposable.add(saveObjectDisposable)
    }

    private fun makeRxJavaCallWithReturnValue() {

        /*
            check if cache isn't to old, if it is, we are telling repository to refresh it objects and download them
            from remote source
        */
        if (firstLoad || cacheExpirationManager.isCacheOlderThanFifteenMinutes(MyObjectRepository.USER_OBJECT_LAST_REFRESH)) {
            firstLoad = false
            myObjectRepository.refreshObjects()
        }

        val myObjectsDisposable = myObjectRepository
            .getMyObjects()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // TODO do something here with returned value, after getting it asynchronously with RxJava
            }, {
                // TODO do something onError here (if we can't get my object list properly)
                    throwable: Throwable ->
                Log.e("example presenter", "error when fetching myObjects list", throwable)
            })
        compositeDisposable.add(myObjectsDisposable)
    }

    override fun dropView() {
        compositeDisposable.clear()
        view = null
    }
}