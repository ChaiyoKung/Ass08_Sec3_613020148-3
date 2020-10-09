package com.id6130201483.ass08

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.emp_item_layout.view.*

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.text_empItemName
    val tvGender = view.text_empItemGender
    val tvEmail = view.text_empItemEmail
    val tvSalary = view.text_empItemSalary
}