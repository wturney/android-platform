package com.wtl.base.api

import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import com.wtl.base.api.service.ResumeService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import java.net.SocketTimeoutException
import java.util.concurrent.atomic.AtomicInteger

enum class MockFailureType(val exception: Exception) {
    NULL_POINTER(NullPointerException("Mock NullPointerException")),
    SOCKET_TIMEOUT(SocketTimeoutException("Mock SocketTimeoutException"))
}

class MockResumeService(
    private val delegate: BehaviorDelegate<ResumeService>
) : ResumeService {

    // Public for Espresso idling resource
    val idle: Boolean get() = executingRequests.get() == 0
    var idleCallback: (() -> Unit)? = null

    private val executingRequests = AtomicInteger(0)

    private val employers = listOf(
        Employer(
            1,
            "TRED",
            null,
            "Lead Mobile Engineer"
        ),
        Employer(
            2,
            "Recreational Equipment, Inc.",
            "REI Co-op",
            "Senior Software Engineer"
        )
    )

    private val hobbies = listOf(
        Hobby(
            1,
            "Cycling"
        ),
        Hobby(
            2,
            "Snow Sports"
        ),
        Hobby(
            3,
            "Bass Guitar"
        ),
        Hobby(
            4,
            "DotA 2"
        )
    )

    override fun getEmployers(): Single<List<Employer>> {
        return delegate.returning(Calls.response(Response.success(employers)))
            .getEmployers()
            .withIdlingCallback()
    }

    override fun getHobbies(): Single<List<Hobby>> {
        return delegate.returning(Calls.response(Response.success(hobbies)))
            .getHobbies()
            .withIdlingCallback()
    }

    /**
     * Tracks mock requests for the purposes of signaling Espresso idling resources
     */
    private fun <T> Single<T>.withIdlingCallback(): Single<T> {
        return this
            .doOnSubscribe { executingRequests.incrementAndGet() }
            .doFinally {
                executingRequests.decrementAndGet()
                AndroidSchedulers.mainThread().scheduleDirect {
                    if (executingRequests.get() == 0) {
                        idleCallback?.invoke()
                    }
                }
            }
    }
}
