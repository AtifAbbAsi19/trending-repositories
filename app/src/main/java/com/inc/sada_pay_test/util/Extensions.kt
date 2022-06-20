package com.inc.sada_pay_test.util

import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


fun ImageView.loadServerImage(url: String?) {

    if (url.isNullOrEmpty()) return
    Picasso.get()
        .load(url)
        .into(this)

}

@BindingAdapter("url")
fun ShapeableImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    this.loadServerImage(url)
}


fun View.expand() {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, -height.toFloat(), 0f)
    animate.duration = 200
    animate.fillAfter = true
    startAnimation(animate)
}

fun View.collapse() {
    val animate = TranslateAnimation(0f, 0f, 0f, -height.toFloat())
    animate.duration = 200
    animate.fillAfter = true
    startAnimation(animate)
}