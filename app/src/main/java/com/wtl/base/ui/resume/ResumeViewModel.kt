package com.wtl.base.ui.resume

import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import com.wtl.base.repository.ResumeRepository
import com.wtl.base.ui.BaseViewModel
import com.wtl.base.ui.Resource
import com.wtl.base.ui.ResourceError
import com.wtl.base.ui.ResourceStatus
import com.wtl.base.util.NonNullMutableLiveData
import com.wtl.base.util.lazyLiveData
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxkotlin.plusAssign

typealias EmployersResource = Resource<List<Employer>, ResourceError>
typealias HobbiesResource = Resource<List<Hobby>, ResourceError>

class ResumeViewModel(
    private val repository: ResumeRepository
) : BaseViewModel() {

    val employers: NonNullMutableLiveData<EmployersResource> by lazyLiveData(
        EmployersResource(ResourceStatus.LOADING),
        this::loadEmployers
    )

    val hobbies: NonNullMutableLiveData<HobbiesResource> by lazyLiveData(
        HobbiesResource(ResourceStatus.LOADING),
        this::loadHobbies
    )

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
        employers.value = EmployersResource(ResourceStatus.ERROR, employers.value.data, ResourceError.UNKNOWN)
    }

    private fun onHobbiesError(err: Throwable) {
        hobbies.value = HobbiesResource(ResourceStatus.ERROR, hobbies.value.data, ResourceError.UNKNOWN)
    }
}

