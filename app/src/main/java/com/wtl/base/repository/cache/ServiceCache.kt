package com.wtl.base.repository.cache

import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby

interface ServiceCache {

    var employers: List<Employer>?
    var hobbies: List<Hobby>?

    fun clear()

}
