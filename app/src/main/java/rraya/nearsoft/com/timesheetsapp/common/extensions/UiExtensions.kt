package rraya.nearsoft.com.timesheetsapp.common.extensions

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, activity: Activity) {
    Glide.with(activity).load(url).into(this)
}
