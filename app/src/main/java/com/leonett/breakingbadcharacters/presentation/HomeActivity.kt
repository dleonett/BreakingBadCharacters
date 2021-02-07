package com.leonett.breakingbadcharacters.presentation

import com.leonett.breakingbadcharacters.databinding.ActivityMainBinding
import com.leonett.breakingbadcharacters.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>() {

    override val binding: ActivityMainBinding
        get() = ActivityMainBinding.inflate(layoutInflater)

    override fun initVars() {

    }

    override fun initViews() {

    }

}
