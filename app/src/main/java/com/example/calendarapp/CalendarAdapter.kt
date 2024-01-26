package com.example.calendarapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(daysOfMonth : ArrayList<String>, onItemListener: OnItemListener) : RecyclerView.Adapter<CalendarViewHolder>() {

    val daysOfMonths : ArrayList<String> = daysOfMonth
    val onItemListener : OnItemListener = onItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = layoutInflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams : ViewGroup.LayoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.16).toInt()
        return CalendarViewHolder(view, onItemListener)
    }

    override fun getItemCount(): Int {
        return daysOfMonths.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.dayOfMonth.setText(daysOfMonths.get(position))
    }

    interface OnItemListener {
        fun onItemClick(position : Int, dayText : String)
    }
}