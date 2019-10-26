package com.wtl.base.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.wtl.base.R
import com.wtl.debug.ui.ContentViewWrapper
import io.reactivex.disposables.CompositeDisposable
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

    /**
     * Composite disposables for binding UI events. This is cleared in the onPause() lifecycle event.
     */
    protected val disposables = CompositeDisposable()

    /**
     * In debug builds this implementation will wrap the root view of the activity
     * with a debug drawer layout. In release builds the wrap is a no-op
     */
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

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

    fun Int.toast(duration: Int = Toast.LENGTH_SHORT) { Toast.makeText(this@BaseActivity, this, duration).show() }
    fun Int.toPixelOffset(): Int = resources.getDimensionPixelOffset(this)
    fun Int.toColor(): Int = ContextCompat.getColor(this@BaseActivity, this)
    fun Int.toDrawable(): Drawable? = ContextCompat.getDrawable(this@BaseActivity, this)
}
