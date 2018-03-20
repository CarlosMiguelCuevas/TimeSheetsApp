package rraya.nearsoft.com.timesheetsapp.common.extensions

import android.support.v4.app.FragmentActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String, activity: FragmentActivity?) {
    activity?.let {
        Glide.with(activity).load(url)
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(this)
    }
}

fun ImageView.preLoadImage(url: String, activity: FragmentActivity?) {
    activity?.let {
        //        TODO:UPDETE to use no deprectaed method
        Glide.with(activity).load(url).downloadOnly(250, 250)
    }
}
