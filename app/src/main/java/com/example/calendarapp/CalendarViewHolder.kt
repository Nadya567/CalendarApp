package com.example.calendarapp

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val dayOfMonth : TextView = itemView.findViewById(R.id.cellDayText)
    lateinit var onItemListener : CalendarAdapter.OnItemListener

    constructor(itemView : View, onItemListener: CalendarAdapter.OnItemListener) : this(itemView) {
        super.itemView
        this.onItemListener = onItemListener
        itemView.setOnClickListener(this)
    }

    @Override
    override fun onClick (view : View)
    {
        onItemListener.onItemClick(adapterPosition, dayOfMonth.text.toString())
    }
}