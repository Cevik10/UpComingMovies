package com.hakancevik.moviesapp.util

import androidx.databinding.BindingAdapter


@BindingAdapter("bind:setToolbarTitle")
fun setToolbarTitle(view: MyToolbar, title: String?) {
    view.getToolbarLogo().gone()
    view.getTitle().text = title
    view.getTitle().visible()
}

@BindingAdapter("bind:setBackIcon")
fun setBackIcon(view: MyToolbar, isVisible: Boolean){
    if(isVisible) view.getBackIcon().visible() else view.getBackIcon().gone()
}

@BindingAdapter("bind:setCounter")
fun setCounter(view: MyToolbar, counterText: String?){
    view.getCounter().text = counterText
}

