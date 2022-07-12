/*
 * Created by Muhamad Syafii
 * Monday, 04/04/2022
 * Copyright (c) 2022 by Gibox Digital Asia.
 * All Rights Reserve
 */

package com.gibox.testandroid.view.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.gibox.testandroid.R
import com.gibox.testandroid.core.data.auth.source.remote.request.LoginRequest
import com.gibox.testandroid.databinding.ActivityLoginBinding
import com.gibox.testandroid.view.common.BaseSimpleActivity
import com.gibox.testandroid.view.ui.listuser.ListUserActivity
import com.gibox.testandroid.view.ui.viewmodel.MainViewModel
import com.gibox.testandroid.view.widget.AppErrorPage
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseSimpleActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun findPageContentView(): View = binding.content

    override fun findPageErrorView(): AppErrorPage = binding.errorPage

    override fun initLiveDataObservers() {
        viewModel.isLoadingRequestLogin.observe(this) { binding.loadingPage.isVisible = it }
        viewModel.isErrorRequestLogin.observe(this) { handleErrorPage(it) }
        viewModel.dataRequestLogin.observe(this) {
            it?.let { startActivity(Intent(this, ListUserActivity::class.java)) }
        }
    }

    override fun initViews() {
        binding.btnLogin.setOnClickListener {
            if (isEmailValid() && isPasswordValid()) {
                viewModel.requestLogin(
                    LoginRequest(
                        email = binding.edtEmail.text.toString(),
                        password = binding.edtPassword.text.toString()
                    )
                )
            }
        }
    }

    private fun isEmailValid(): Boolean {
        val errorMessage = if (binding.edtEmail.text.isNullOrEmpty()) getString(R.string.empty_field_error_message)
        else String()
        binding.tlEmail.error = errorMessage
        return errorMessage.isEmpty()
    }

    private fun isPasswordValid(): Boolean {
        val errorMessage = if (binding.edtPassword.text.isNullOrEmpty()) getString(R.string.empty_field_error_message)
        else String()
        binding.tlPassword.error = errorMessage
        return errorMessage.isEmpty()
    }

}
