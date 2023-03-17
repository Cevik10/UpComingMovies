package com.hakancevik.moviesapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.hakancevik.moviesapp.R
import com.hakancevik.moviesapp.databinding.ToolbarBinding

class MyToolbar: ConstraintLayout {

    private var binding: ToolbarBinding

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.toolbar, this, true)
    }

    //fun getMenuIcon(): ImageView = binding.ivMenu
    fun getBackIcon(): ImageView = binding.ivBack
    fun getTitle(): TextView = binding.tvToolbarText
    fun getCounter(): TextView = binding.tvCounter
    fun getToolbarLogo(): ImageView = binding.ivToolbarLogo
    fun hide() = binding.root.gone()
    fun show() = binding.root.visible()
}