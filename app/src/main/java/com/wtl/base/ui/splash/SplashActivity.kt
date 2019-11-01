package com.wtl.base.ui.splash

import android.os.Bundle
import com.wtl.base.ui.BaseActivity
import com.wtl.base.ui.resume.ResumeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    override val contentLayoutId = 0

    private val model: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(ResumeActivity.newIntent(applicationContext))
        finish()
    }

}
