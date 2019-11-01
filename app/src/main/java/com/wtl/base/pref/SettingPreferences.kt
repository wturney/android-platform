package com.wtl.base.pref

import com.f2prateek.rx.preferences2.RxSharedPreferences

class SettingPreferences(prefs: RxSharedPreferences) {
    val nightMode = prefs.getBoolean("nightMode", false)
}
