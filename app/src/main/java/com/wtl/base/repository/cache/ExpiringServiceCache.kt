package com.wtl.base.repository.cache

import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import net.jodah.expiringmap.ExpiringMap
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
class ExpiringServiceCache() : ServiceCache {

    var enabled: Boolean = true
        set(value) {
            field = value
            clear()
        }

    override var employers: List<Employer>?
        get() = when (enabled) {
            true -> generalExpiringMap["employers"] as? List<Employer>
            false -> null
        }
        set(value) {
            generalExpiringMap["employers"] = value
        }

    override var hobbies: List<Hobby>?
        get() = when (enabled) {
            true -> generalExpiringMap["hobbies"] as? List<Hobby>
            false -> null
        }
        set(value) {
            generalExpiringMap["hobbies"] = value
        }

    private val generalExpiringMap = ExpiringMap.builder()
        .maxSize(10)
        .expiration(1, TimeUnit.HOURS)
        .build<String, Any>()

    override fun clear() {
        generalExpiringMap.clear()
    }
}
