package com.example.orpuwupetup.basicmvpproject.utils.ui

import android.content.Context
import android.util.TypedValue

class DensityPixelsProvider(private val context: Context) {

    fun formatToDensityPixels(dimension: Int): Int {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimension.toFloat(), context.resources.displayMetrics))
    }
}