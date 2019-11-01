package com.wtl.base.ui.employer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wtl.base.R
import com.wtl.base.api.model.Employer
import com.wtl.base.ui.BaseActivity
import kotlinx.android.synthetic.main.view_employer_role.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder

class EmployerActivity : BaseActivity() {

    override val contentLayoutId = R.layout.act_employer

    private val model: EmployerViewModel by viewModel { parametersOf(intent.getParcelableExtra<Employer>(EXTRA_EMPLOYER)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initViewModel() {
        title = model.employer.shortName ?: model.employer.name
        startDateView.text = Formatters.EMPLOYMENT_DATE.format(model.employer.role)
    }

    companion object {
        private const val EXTRA_EMPLOYER = "EXTRA_EMPLOYER"

        fun newIntent(context: Context, employer: Employer): Intent = Intent(context, EmployerActivity::class.java)
            .putExtra(EXTRA_EMPLOYER, employer)
    }
}

object Formatters {

    val EMPLOYMENT_DATE = DateTimeFormatter.ofPattern("MMM. yyyy")

}
