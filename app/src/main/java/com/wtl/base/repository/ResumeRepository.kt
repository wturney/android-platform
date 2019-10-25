package com.wtl.base.repository

import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import com.wtl.base.api.service.ResumeService
import com.wtl.base.repository.cache.ServiceCache
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ResumeRepository(
    private val service: ResumeService,
    private val cache: ServiceCache
) {

    fun getEmployers(includeCache: Boolean = true): Single<List<Employer>> {
        if (includeCache) {
            cache.employers?.let { return Single.just(it) }
        }

        return service.getEmployers()
            .doOnSuccess { cache.employers = it }
            .subscribeOn(Schedulers.io())
    }

    fun getHobbies(includeCache: Boolean = true): Single<List<Hobby>> {
        if (includeCache) {
            cache.hobbies?.let { return Single.just(it) }
        }

        return service.getHobbies()
            .doOnSuccess { cache.hobbies = it }
            .subscribeOn(Schedulers.io())
    }

}
