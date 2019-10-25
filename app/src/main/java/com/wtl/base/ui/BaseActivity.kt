package com.wtl.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.wtl.base.R
import com.wtl.debug.ui.ContentViewWrapper
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    /**
     * Layout ID that would normally be passed to setContentView(). This is inflated into a
     * injected wrapper (which in production is the default activity root view). This
     * is done to enable injecting a debug drawer wrapper in non-prod builds.
     *
     * A value of 0 will skip the wrapping process
     */
    protected abstract val contentLayoutId: Int

    private val contentViewWrapper: ContentViewWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (contentLayoutId != 0) {
            val rootView = contentViewWrapper.wrap(this)
            layoutInflater.inflate(contentLayoutId, rootView)
        }

        findViewById<Toolbar>(R.id.toolbar)?.let { toolbar ->
            setSupportActionBar(toolbar)
        }
    }

    fun Int.toPixelOffset(): Int = resources.getDimensionPixelOffset(this)
}
