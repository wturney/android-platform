package com.wtl.base

import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wtl.base.ui.resume.employer.EmployerAdapter
import com.wtl.base.ui.resume.employer.EmployerItem
import com.wtl.base.ui.resume.hobby.HobbyAdapter
import com.wtl.base.ui.resume.hobby.HobbyItem
import kotlinx.android.synthetic.main.view_resume_employers.*
import kotlinx.android.synthetic.main.view_resume_hobbies.*

class HorizontalOffsetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildLayoutPosition(view)
        if (position > 0) {
            outRect.left = offset
        }
    }
}

class MainActivity : BaseActivity() {

    override val contentLayoutId = R.layout.act_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEmployers()
        initHobbies()
    }

    private fun initHobbies() {
        hobbyRecyclerView.addItemDecoration(
            HorizontalOffsetDecoration(
                resources.getDimensionPixelOffset(
                    R.dimen.spacing_small
                )
            )
        )
        hobbyRecyclerView.adapter = HobbyAdapter().apply {
            items = listOf(
                HobbyItem(
                    1,
                    "Cycling",
                    ContextCompat.getColor(this@MainActivity, R.color.hobbyCycling),
                    R.drawable.ic_bike_60dp
                ),
                HobbyItem(
                    2,
                    "Snow Sports",
                    ContextCompat.getColor(this@MainActivity, R.color.hobbySnow),
                    R.drawable.ic_snow_60dp
                ),
                HobbyItem(
                    3,
                    "Bass Guitar",
                    ContextCompat.getColor(this@MainActivity, R.color.hobbyBass),
                    R.drawable.ic_music_note_60dp
                ),
                HobbyItem(
                    4,
                    "DotA 2",
                    ContextCompat.getColor(this@MainActivity, R.color.hobbyDota),
                    R.drawable.ic_dota2_60dp
                )
            )
        }
    }

    private fun initEmployers() {
        employerRecyclerView.addItemDecoration(
            HorizontalOffsetDecoration(
                resources.getDimensionPixelOffset(
                    R.dimen.spacing_small
                )
            )
        )
        employerRecyclerView.adapter = EmployerAdapter().apply {
            items = listOf(
                EmployerItem(
                    1,
                    "TRED",
                    "Lead Mobile Engineer",
                    ContextCompat.getColor(this@MainActivity, R.color.employerTred),
                    R.drawable.ic_tred_160dp
                ),
                EmployerItem(
                    2,
                    "Recreational Equipment, Inc.",
                    "Senior Software Engineer",
                    ContextCompat.getColor(this@MainActivity, R.color.employerRei),
                    R.drawable.ic_rei_160dp
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.act_main, menu)
        return true
    }

}
