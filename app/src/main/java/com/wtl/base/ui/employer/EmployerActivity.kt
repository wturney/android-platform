package com.wtl.base.ui.employer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TableLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.wtl.base.R
import com.wtl.base.api.model.Employer
import com.wtl.base.ui.BaseActivity
import com.wtl.base.ui.util.Formatters
import kotlinx.android.synthetic.main.act_employer.*
import kotlinx.android.synthetic.main.view_employer_role.view.*
import kotlinx.android.synthetic.main.view_employer_role_date.view.*
import kotlinx.android.synthetic.main.view_employer_role_description.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EmployerActivity : BaseActivity() {

    override val contentLayoutId = R.layout.act_employer

    private val model: EmployerViewModel by viewModel {
        parametersOf(
            intent.getParcelableExtra<Employer>(
                EXTRA_EMPLOYER
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initViewModel() {
    }

    private fun initViews() {
        val employer = model.employer

        title = employer.shortName ?: employer.name



        employer.roles.forEachIndexed { index, role ->
            val roleLayout = layoutInflater.inflate(
                R.layout.view_employer_role,
                employerLayout,
                false
            ) as LinearLayout

            roleLayout.roleNameView.text = role.name

            role.dates.forEach { date ->
                val dateLayout = layoutInflater.inflate(
                    R.layout.view_employer_role_date,
                    roleLayout.roleDatesLayout,
                    false
                ) as LinearLayout
                dateLayout.startView.text = Formatters.EMPLOYMENT_DATE.format(date.first)
                dateLayout.endView.text = date.second?.let { Formatters.EMPLOYMENT_DATE.format(it) } ?: "Present"
                roleLayout.roleDatesLayout.addView(dateLayout, TableLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
            }

            role.description.forEach { desc ->
                val descLayout = layoutInflater.inflate(
                    R.layout.view_employer_role_description,
                    roleLayout.roleDescriptionsLayout,
                    false
                ) as LinearLayout
                descLayout.descriptionView.text = desc
                roleLayout.roleDescriptionsLayout.addView(descLayout, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
            }

            role.technologies.onEach { tech ->
                val techView = layoutInflater.inflate(
                    R.layout.view_employer_role_technology,
                    roleLayout.roleTechnologiesLayout,
                    false
                ) as Chip
                techView.text = tech
                roleLayout.roleTechnologiesLayout.addView(techView, ChipGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
            }

            employerLayout.addView(
                roleLayout,
                LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    .apply {
                        topMargin = if (index > 0) R.dimen.spacing_medium.toPixelOffset() else 0
                    }
            )
        }
    }

    companion object {
        private const val EXTRA_EMPLOYER = "EXTRA_EMPLOYER"

        fun newIntent(context: Context, employer: Employer): Intent =
            Intent(context, EmployerActivity::class.java)
                .putExtra(EXTRA_EMPLOYER, employer)
    }
}

