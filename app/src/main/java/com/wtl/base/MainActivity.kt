package com.wtl.base

import android.os.Bundle
import android.view.Menu

class MainActivity : BaseActivity() {

    override val contentLayoutId = R.layout.act_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.act_main, menu)
        return true
    }

}
