/*
 * Created by Muhamad Syafii
 * Monday, 04/04/2022
 * Copyright (c) 2022 by Gibox Digital Asia.
 * All Rights Reserve
 */

package com.gibox.testandroid.view.ui.listuser

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gibox.testandroid.databinding.ActivityListUserBinding
import com.gibox.testandroid.view.adapter.UserAdapter
import com.gibox.testandroid.view.common.BaseSimpleActivity
import com.gibox.testandroid.view.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ListUserActivity : BaseSimpleActivity() {

    private val binding by lazy { ActivityListUserBinding.inflate(layoutInflater) }

    override val viewModel by viewModel<MainViewModel>()

    val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun initViews() {
        super.initViews()
        val items = viewModel.userList
        binding.rvContent.apply {
            adapter = this@ListUserActivity.adapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
        lifecycleScope.launch {
            items.collectLatest {
                adapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collect {
                binding.appendProgress.isVisible = it.source.append is LoadState.Loading
            }
        }
        adapter.addLoadStateListener {
            binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
        }
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

    override fun onBackPressed() {
        this.finish()
    }
}
