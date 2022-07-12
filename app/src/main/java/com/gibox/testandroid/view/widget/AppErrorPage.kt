package com.gibox.testandroid.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.gibox.testandroid.R
import com.gibox.testandroid.databinding.ErrorPageBinding

class AppErrorPage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var onButtonClick: (() -> Unit)? = null

    private var binding = ErrorPageBinding
        .inflate(LayoutInflater.from(context), this, true)

    override fun onFinishInflate() {
        super.onFinishInflate()
        with(binding) {
            btnOk.setOnClickListener {
                onButtonClick?.invoke()
            }
        }
    }

    fun setError(errorMessage: String, onButtonClick: () -> Unit) {
        with(binding) {
            this@AppErrorPage.onButtonClick = onButtonClick
            txtErrorMessage.text = errorMessage.ifEmpty { context.getString(R.string.general_error_message) }
        }
    }

}
