package com.example.orpuwupetup.basicmvpproject.utils.ui

import android.animation.*
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.ScaleAnimation


fun getViewPressReleaseAnimationAnimatorSet(view: View): AnimatorSet {
    val putViewDown = ObjectAnimator.ofFloat(view, "translationZ", 0f).apply {
        duration = 100
    }

    val squashViewX = ObjectAnimator.ofFloat(view, "scaleX", 1f).apply {
        duration = 100
    }

    val squashViewY = ObjectAnimator.ofFloat(view, "scaleY", 1f).apply {
        duration = 100
    }

    return AnimatorSet().apply {
        play(putViewDown).with(squashViewX).with(squashViewY)
    }
}

fun getViewPressedAnimationAnimatorSet(view: View, scaleSize: Float): AnimatorSet {
    val liftView = ObjectAnimator.ofFloat(view, "translationZ", 10f).apply {
        duration = 100
    }

    val stretchViewX = ObjectAnimator.ofFloat(view, "scaleX", scaleSize).apply {
        duration = 100
    }

    val stretchViewY = ObjectAnimator.ofFloat(view, "scaleY", scaleSize).apply {
        duration = 100
    }

    return AnimatorSet().apply {
        play(liftView).with(stretchViewX).with(stretchViewY)
    }
}

inline fun View.setAnimatedClick(crossinline doOnClick: () -> Unit, scaleSize: Float = 1.03f) {

    with(this) {

        val pressAnimation = getViewPressedAnimationAnimatorSet(this, scaleSize)
        val releaseAnimation = getViewPressReleaseAnimationAnimatorSet(this)

        setOnTouchListener { v, event ->

            event?.let {
                if (it.action == MotionEvent.ACTION_UP) {

                    releaseAnimation.start()
                    doOnClick()

                    return@setOnTouchListener true
                } else if (it.action == MotionEvent.ACTION_DOWN) {

                    pressAnimation.start()

                    return@setOnTouchListener true
                } else if (it.action == MotionEvent.ACTION_CANCEL) {

                    releaseAnimation.start()

                    return@setOnTouchListener true
                } else {
                    return@setOnTouchListener false
                }
            }
            return@setOnTouchListener false
        }
    }
}

private fun getShowNotificationAnimation(view: View): ObjectAnimator {
    val hideViewTopPosition = 1f - view.height
    return ObjectAnimator.ofFloat(view, "translationY", if(view.y == hideViewTopPosition + view.height) 0f else -(hideViewTopPosition)).apply {
        duration = 140
    }
}

private fun getHideNotificationAnimation(view: View): ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "translationY",  (-view.height).toFloat()).apply {
        duration = 80
    }.apply {
        addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.INVISIBLE
            }
        })
    }
}

fun View.animateShowNotification() {
    this.visibility = View.VISIBLE
    getShowNotificationAnimation(this).start()
}

fun View.animateHideNotification(){
    this.clearAnimation()
    getHideNotificationAnimation(this).start()
}

fun View.animateShowNotification(timeUntilHide: Long){
    this.stopAnimations()

    this.visibility = View.VISIBLE

    val animSet = AnimatorSet().apply {
        playSequentially(getShowNotificationAnimation(this@animateShowNotification), getHideNotificationAnimation(this@animateShowNotification).apply {
            startDelay = timeUntilHide
        })
        start()
    }

    this.tag = animSet
}

fun View.stopAnimations() {

    this.tag?.let {
        val previousAnimSet = it as AnimatorSet
        previousAnimSet.pause()
        this.tag = null
    }
}

fun View.hideProgressBar() {
    this.animation = ScaleAnimation(1f, 1f, 1f, 0f, 0f, 0f).apply {
        fillAfter = true
        duration = 300
        start()
    }
}

fun View.startProgressIndicatorMoving(progressTrackEnd: Float) {

    val animSet = AnimatorSet().apply {
        playTogether(
            getProgressIndicatorScalingAnimation(this@startProgressIndicatorMoving),
            getProgressIndicatorMovingAnimation(this@startProgressIndicatorMoving, progressTrackEnd))
        start()
    }

    this.tag = animSet

}

private fun getProgressIndicatorMovingAnimation(view: View, progressTrackEnd: Float): ObjectAnimator {

    return ObjectAnimator.ofFloat(view, "translationX", progressTrackEnd + view.width + 10 ).apply {
        interpolator = AccelerateDecelerateInterpolator()
        duration = 1400
        repeatMode = ValueAnimator.RESTART
        repeatCount = ValueAnimator.INFINITE
    }
}

private fun getProgressIndicatorScalingAnimation(view: View): ObjectAnimator {
    return ObjectAnimator.ofFloat(view, "scaleX", 1f, 2.5f).apply {
        view.pivotX = 1f
        duration = 700
        interpolator = AccelerateInterpolator()
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
    }
}

fun View.showViewViaAlphaIncrease(animationDuration: Long = 150) {
    this@showViewViaAlphaIncrease.visibility = View.VISIBLE
    ObjectAnimator.ofFloat(this, "alpha", 0f, 0.8f).apply {
        duration = animationDuration
        start()
    }
}