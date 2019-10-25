package com.wtl.base.ui.resume

import android.os.Bundle
import android.view.Menu
import com.wtl.base.R
import com.wtl.base.ui.BaseActivity
import com.wtl.base.ui.resume.employer.EmployerAdapter
import com.wtl.base.ui.resume.employer.EmployerItem
import com.wtl.base.ui.resume.hobby.HobbyAdapter
import com.wtl.base.ui.resume.hobby.HobbyItem
import com.wtl.base.ui.util.recycler.HorizontalOffsetDecoration
import kotlinx.android.synthetic.main.view_resume_employers.*
import kotlinx.android.synthetic.main.view_resume_hobbies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResumeActivity : BaseActivity() {

    override val contentLayoutId = R.layout.act_main

    private val model: ResumeViewModel by viewModel()
    private val hobbyAdapter = HobbyAdapter()
    private val employerAdapter = EmployerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEmployers()
        initHobbies()
        initViewModel()
    }

    private fun initViewModel() {
        model.employers.observe(this) { onEmployersChanged(it) }
        model.hobbies.observe(this) { onHobbiesChanged(it) }
    }

    private fun onEmployersChanged(res: EmployersResource) {
        res.data?.let { employerAdapter.items = it.map(::EmployerItem) }
    }

    private fun onHobbiesChanged(res: HobbiesResource) {
        res.data?.let { hobbyAdapter.items = it.map(::HobbyItem) }
    }

    private fun initHobbies() {
        hobbyRecyclerView.addItemDecoration(HorizontalOffsetDecoration(R.dimen.spacing_small.toPixelOffset()))
        hobbyRecyclerView.adapter = hobbyAdapter
    }

    private fun initEmployers() {
        employerRecyclerView.addItemDecoration(HorizontalOffsetDecoration(R.dimen.spacing_small.toPixelOffset()))
        employerRecyclerView.adapter = employerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.act_main, menu)
        return true
    }
}
