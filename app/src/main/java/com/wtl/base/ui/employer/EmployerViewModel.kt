package com.wtl.base.ui.employer

import com.wtl.base.api.model.Employer
import com.wtl.base.ui.BaseViewModel
import com.wtl.base.ui.util.arch.SingleLiveEvent
import com.wtl.base.util.NonNullMutableLiveData

class EmployerViewModel(
    val employer: Employer
) : BaseViewModel() {
    val events = Events()

    class Events {
        val website = SingleLiveEvent<String>()
        val playStore = SingleLiveEvent<String>()
    }
}

