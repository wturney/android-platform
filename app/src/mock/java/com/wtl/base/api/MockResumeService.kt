package com.wtl.base.api

import com.wtl.base.api.model.AppPlatform
import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import com.wtl.base.api.model.Role
import com.wtl.base.api.service.ResumeService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import org.threeten.bp.LocalDate
import org.threeten.bp.Month
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
            listOf(
                Role(
                    "Lead Mobile Engineer",
                    listOf(
                        "Created and maintained the TRED native apps (Android, iOS) and their associated build pipelines.",
                        "Oversaw interviewing and on-boarding of mobile developers.",
                        "Provided limited developer support for server-side and front-end projects."
                    ),
                    listOf(
                        "Android",
                        "iOS",
                        "Kotlin",
                        "Swift",
                        "Jenkins",
                        "Bitrise",
                        "TypeScript",
                        "Node.js",
                        "React.js"
                    ),
                    listOf(
                        Pair(LocalDate.of(2018, Month.JANUARY, 1), LocalDate.of(2019, Month.NOVEMBER, 1))
                    )

                )
            ),
            "https://www.tred.com",
            mapOf(
                AppPlatform.IOS to "https://apps.apple.com/us/app/tred/id1070071394",
                AppPlatform.ANDROID to "https://play.google.com/store/apps/details?id=com.tred.mobile"
            )
        ),
        Employer(
            2,
            "Recreational Equipment, Inc.",
            "REI Co-op",
            listOf(
                Role(
                    "Senior Software Engineer (Mobile)",
                    listOf(
                        "Maecenas laoreet efficitur accumsan.",
                        "Sed vitae placerat nunc. Nunc vehicula elementum risus sit amet consectetur.",
                        "Ut pretium arcu et dolor dapibus, vitae finibus metus pretium."
                    ),
                    listOf(
                        "Android",
                        "Java",
                        "Kotlin",
                        "Jenkins"
                    ),
                    listOf(
                        Pair(LocalDate.of(2015, Month.FEBRUARY, 1), LocalDate.of(2016, Month.OCTOBER, 1))
                    )
                ),
                Role(
                    "Senior Software Engineer (DevOps)",
                    listOf(
                        "Praesent ac mi mattis, semper sapien at, iaculis magna.",
                        "Integer luctus dui vel elementum euismod."
                    ),
                    listOf(
                        "Jenkins",
                        "Docker",
                        "AWS",
                        "EC2",
                        "ECS",
                        "RDS",
                        "Java",
                        "Kotlin"
                    ),
                    listOf(
                        Pair(LocalDate.of(2014, Month.AUGUST, 1), LocalDate.of(2015, Month.FEBRUARY, 1)),
                        Pair(LocalDate.of(2016, Month.OCTOBER, 1), LocalDate.of(2017, Month.JUNE, 1))
                    )
                )
            ),
            "https://www.rei.com",
            mapOf(
                AppPlatform.ANDROID to "https://play.google.com/store/apps/details?id=com.ubermind.rei"
            )
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
