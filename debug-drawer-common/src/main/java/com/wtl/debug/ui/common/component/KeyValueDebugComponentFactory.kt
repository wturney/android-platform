package com.wtl.debug.ui.common.component

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import com.wtl.debug.common.R
import kotlinx.android.synthetic.main.dbg_cmp_key_value_item.view.*

class KeyValueDebugComponentFactory(private vararg val pairs: Pair<String, String>) :
    DebugComponentFactory {

    override fun createComponent(context: Context, parent: ViewGroup): DebugComponent {
        val inflater = LayoutInflater.from(context)
        val table = inflater.inflate(R.layout.dbg_cmp_key_value, parent, false) as TableLayout
        pairs.forEachIndexed { index, pair ->
            val row = inflater.inflate(R.layout.dbg_cmp_key_value_item, table, false) as TableRow
            row.dbgKeyView.text = pair.first
            row.dbgValueView.text = pair.second
            val params = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                if (index > 0) {
                    topMargin = context.resources.getDimensionPixelSize(R.dimen.spacing_small)
                }
            }
            table.addView(row, params)
        }
        return ViewDebugComponent(table)
    }

}
