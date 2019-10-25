package com.wtl.base.api.service

import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import io.reactivex.Single
import retrofit2.http.GET

interface ResumeService {

    @GET("/employment")
    fun getEmployers(): Single<List<Employer>>

    @GET("/hobby")
    fun getHobbies(): Single<List<Hobby>>

}
