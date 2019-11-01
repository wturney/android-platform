package com.wtl.base.ui.util.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import android.widget.FrameLayout
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.checkedChanges
import com.wtl.base.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.view_list_switch_button.view.*


class ListSwitchButton : FrameLayout, Checkable {

    private val disposables = CompositeDisposable()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        inflate(context, R.layout.view_list_switch_button, this)

        attrs?.let {
            val typedArray = context.theme.obtainStyledAttributes(it, R.styleable.ListSwitchButton, 0, 0)
            switchView.isChecked = typedArray.getBoolean(R.styleable.ListSwitchButton_android_checked, false)
            buttonView.text = typedArray.getString(R.styleable.ListSwitchButton_android_text)
            typedArray.recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        disposables += buttonView.clicks().subscribe { toggle() }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        disposables.clear()
    }

    override fun isChecked() = switchView.isChecked
    override fun toggle() = switchView.toggle()
    override fun setChecked(checked: Boolean) {
        switchView.isChecked = checked
    }

    fun checkedChanges() = switchView.checkedChanges()
}
