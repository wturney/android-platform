package com.wtl.base.ui.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wtl.base.R
import com.wtl.base.pref.SettingPreferences
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.frg_settings.*
import org.koin.android.ext.android.inject

class SettingsFragment : BottomSheetDialogFragment() {

    private val settings: SettingPreferences by inject()
    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nightModeView.isChecked = settings.nightMode.get()
    }

    override fun onResume() {
        super.onResume()
        disposables += nightModeView.checkedChanges()
            .skipInitialValue()
            .subscribe { checked ->
                settings.nightMode.set(checked)
                AppCompatDelegate.setDefaultNightMode(
                    if (checked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

}
