package com.wtl.base.ui.resume

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.wtl.base.R
import com.wtl.base.api.model.Employer
import com.wtl.base.api.model.Hobby
import com.wtl.base.ui.BaseActivity
import com.wtl.base.ui.employer.EmployerActivity
import com.wtl.base.ui.resume.employer.EmployerAdapter
import com.wtl.base.ui.resume.employer.EmployerItem
import com.wtl.base.ui.resume.hobby.HobbyAdapter
import com.wtl.base.ui.resume.hobby.HobbyItem
import com.wtl.base.ui.settings.SettingsFragment
import com.wtl.base.ui.util.recycler.HorizontalOffsetDecoration
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.view_resume_contact.*
import kotlinx.android.synthetic.main.view_resume_employers.*
import kotlinx.android.synthetic.main.view_resume_hobbies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResumeActivity : BaseActivity() {

    override val contentLayoutId = R.layout.act_resume

    private val model: ResumeViewModel by viewModel()
    private val hobbyAdapter = HobbyAdapter()
    private val employerAdapter = EmployerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEmployers()
        initHobbies()
        initViewModel()
    }

    override fun onResume() {
        super.onResume()

        disposables += phoneView.clicks().subscribe { model.onPhoneClick() }
        disposables += emailView.clicks().subscribe { model.onEmailClick() }
        disposables += locationView.clicks().subscribe { model.onLocationClick() }
        disposables += employerAdapter.itemClicks().subscribe { model.onEmployerClick(it.employer) }
        disposables += hobbyAdapter.itemClicks().subscribe { model.onHobbyClick(it.hobby) }
    }

    private fun initViewModel() {
        model.employers.observe(this, this::onEmployersChanged)
        model.hobbies.observe(this, this::onHobbiesChanged)
        model.events.dial.observe(this, this::onDial)
        model.events.email.observe(this, this::onEmail)
        model.events.map.observe(this, this::onMap)
        model.events.employerDetails.observe(this, this::onEmployerDetails)
        model.events.hobbyDetails.observe(this, this::onHobbyDetails)
    }

    private fun onHobbyDetails(hobby: Hobby) {
    }

    private fun onEmployerDetails(employer: Employer) {
        startActivity(EmployerActivity.newIntent(this, employer))
    }

    private fun onEmail(email: String) {
        val intent = Intent(ACTION_SENDTO, Uri.parse("mailto:$email")).putExtra(EXTRA_EMAIL, arrayOf(email))
        when {
            packageManager.queryIntentActivities(intent, 0).isNotEmpty() -> startActivity(intent)
            else -> R.string.intent_unavailable_email.toast()
        }
    }

    private fun onDial(number: String) {
        val intent = Intent(ACTION_DIAL, Uri.parse("tel:$number"))
        when {
            packageManager.queryIntentActivities(intent, 0).isNotEmpty() -> startActivity(intent)
            else -> R.string.intent_unavailable_dial.toast()
        }
    }

    private fun onMap(uri: String) {
        startActivity(Intent(ACTION_VIEW, Uri.parse(uri)))
    }

    private fun onEmployersChanged(res: EmployersResource) {
        res.data?.let { employerAdapter.items = it.map(::EmployerItem) }
    }

    private fun onHobbiesChanged(res: HobbiesResource) {
        res.data?.let { hobbyAdapter.items = it.map(::HobbyItem) }
    }

    private fun initHobbies() {
        hobbyRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hobbyRecyclerView.addItemDecoration(HorizontalOffsetDecoration(R.dimen.spacing_small.toPixelOffset()))
        hobbyRecyclerView.adapter = hobbyAdapter
    }

    private fun initEmployers() {
        employerRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        employerRecyclerView.addItemDecoration(HorizontalOffsetDecoration(R.dimen.spacing_small.toPixelOffset()))
        employerRecyclerView.adapter = employerAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == R.id.settingsMenuItem -> {
                SettingsFragment().show(supportFragmentManager, TAG_SETTINGS)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.act_resume, menu)
        return true
    }

    companion object {
        const val TAG_SETTINGS = "settings"

        fun newIntent(context: Context): Intent = Intent(context, ResumeActivity::class.java)
    }
}
