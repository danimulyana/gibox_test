package com.gibox.testandroid.view.common

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.gibox.testandroid.view.widget.AppErrorPage

abstract class BaseSimpleActivity: AppCompatActivity() {

    protected abstract val viewModel: ViewModel

    protected open fun findPageErrorView(): AppErrorPage? = null

    protected open fun findPageContentView(): View? = null

    override fun onStart() {
        super.onStart()
        initViews()
        initLiveDataObservers()
    }

    protected open fun initViews() = Unit

    protected open fun initLiveDataObservers() = Unit

    @CallSuper
    protected open fun handleErrorPage(errorMessage: String?) {
        findPageContentView()?.isVisible = errorMessage == null
        findPageErrorView()?.isVisible = errorMessage != null
        errorMessage?.let {
            findPageErrorView()?.setError(it) { handleErrorPage(null) }
        }
    }

}
