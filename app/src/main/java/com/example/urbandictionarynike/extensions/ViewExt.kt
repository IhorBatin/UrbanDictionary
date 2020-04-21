package com.example.urbandictionarynike.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.urbandictionarynike.R

fun Fragment.isTablet(): Boolean = resources.getBoolean(R.bool.isTablet)

fun FragmentActivity.isTablet(): Boolean = resources.getBoolean(R.bool.isTablet)

fun View.showView(show: Boolean) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

fun View.isInvisible(isInvisible: Boolean) {
    visibility = when (isInvisible) {
        true -> View.INVISIBLE
        false -> View.VISIBLE
    }
}