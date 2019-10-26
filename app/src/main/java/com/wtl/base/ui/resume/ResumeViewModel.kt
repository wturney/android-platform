package com.wtl.base.ui.resume

import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import com.wtl.base.repository.ResumeRepository
import com.wtl.base.ui.BaseViewModel
import com.wtl.base.ui.Resource
import com.wtl.base.ui.ResourceError
import com.wtl.base.ui.ResourceStatus
import com.wtl.base.ui.util.arch.SingleLiveEvent
import com.wtl.base.util.NonNullMutableLiveData
import com.wtl.base.util.lazyLiveData
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

typealias EmployersResource = Resource<List<Employer>, ResourceError>
typealias HobbiesResource = Resource<List<Hobby>, ResourceError>

class ResumeViewModel(
    private val repository: ResumeRepository
) : BaseViewModel() {

    val events = Events()

    val employers: NonNullMutableLiveData<EmployersResource> by lazyLiveData(
        EmployersResource(ResourceStatus.LOADING),
        this::loadEmployers
    )

    val hobbies: NonNullMutableLiveData<HobbiesResource> by lazyLiveData(
        HobbiesResource(ResourceStatus.LOADING),
        this::loadHobbies
    )

    fun onPhoneClick() {
        events.dial.value = "2538675309"
    }

    fun onEmailClick() {
        events.email.value = "wes@hightower-software.com"
    }

    fun onLocationClick() {
        events.map.value = "https://www.google.com/maps/@?api=1&map_action=map&center=47.6072228,-122.3398624&zoom=12"
    }

    private fun loadEmployers() {
        disposables += repository.getEmployers()
            .observeOn(mainThread())
            .subscribe(this::onEmployers, this::onEmployersError)
    }

    private fun loadHobbies() {
        disposables += repository.getHobbies()
            .observeOn(mainThread())
            .subscribe(this::onHobbies, this::onHobbiesError)
    }

    private fun onEmployers(value: List<Employer>) {
        employers.value = EmployersResource(ResourceStatus.IDLE, value)
    }

    private fun onHobbies(value: List<Hobby>) {
        hobbies.value = HobbiesResource(ResourceStatus.IDLE, value)
    }

    private fun onEmployersError(err: Throwable) {
        Timber.e(err, "Employers load failed")
        employers.value = EmployersResource(ResourceStatus.ERROR, employers.value.data, ResourceError.UNKNOWN)
    }

    private fun onHobbiesError(err: Throwable) {
        Timber.e(err, "Hobbies load failed")
        hobbies.value = HobbiesResource(ResourceStatus.ERROR, hobbies.value.data, ResourceError.UNKNOWN)
    }

    class Events {
        val dial = SingleLiveEvent<String>()
        val email = SingleLiveEvent<String>()
        val map = SingleLiveEvent<String>()
    }
}

