package com.hakancevik.moviesapp.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hakancevik.moviesapp.R
import com.makeramen.roundedimageview.RoundedImageView
import com.makeramen.roundedimageview.RoundedTransformationBuilder


fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}


fun RoundedImageView.downloadFromUrl(url: String?,progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_back_arrow)


    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}


fun placeHolderProgressBar(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 7f
        centerRadius = 41f
        start()
    }
}




