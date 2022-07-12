package com.gibox.testandroid.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.gibox.testandroid.R
import com.gibox.testandroid.databinding.LoadingPageBinding

class AppLoadingPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LoadingPageBinding =
        LoadingPageBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onFinishInflate() {
        super.onFinishInflate()
        Glide.with(context).load(R.raw.anim_loader).into(binding.ivLoading)
        binding.container.setOnClickListener {  }
    }

}