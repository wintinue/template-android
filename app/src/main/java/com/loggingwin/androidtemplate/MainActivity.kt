package com.loggingwin.androidtemplate

import android.os.Bundle
import com.loggingwin.androidtemplate.base.BaseActivity
import com.loggingwin.androidtemplate.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}