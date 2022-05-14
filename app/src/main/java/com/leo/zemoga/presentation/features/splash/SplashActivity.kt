package com.leo.zemoga.presentation.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.leo.zemoga.presentation.features.home.MainActivity
import com.leo.zemoga.databinding.ActivitySplashBinding
import com.leo.zemoga.presentation.commons.BaseActivity
import com.leo.zemoga.presentation.commons.BaseViewModel
import com.leo.zemoga.presentation.utils.setOnSafeClickListener
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private val viewModel by inject<SplashViewModel>()
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.status.observe(this) {
            if(it){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                binding.btnTry.visibility = View.VISIBLE
                binding.btnTry.setOnSafeClickListener {
                    viewModel.fetchPosts()
                }
            }
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel

}