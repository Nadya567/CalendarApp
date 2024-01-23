package com.example.calendarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    lateinit var monthYearText : TextView
    lateinit var calendarRecyclerView : RecyclerView
    lateinit var selectedDate : LocalDate

    lateinit var prevButton: Button
    lateinit var nextButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prevButton = findViewById(R.id.previousMonthButton)
        prevButton.setOnClickListener()
        {
            previousMonthAction()
        }

        nextButton = findViewById(R.id.nextMonthButton)
        nextButton.setOnClickListener()
        {
            nextMonthAction()
        }

        initWidgets()
        selectedDate = LocalDate.now()
        setMonthsView()
    }

    fun initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)
    }

    fun setMonthsView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate))

        val daysInMonth : ArrayList<String> = daysInMonthArray(selectedDate)

        val calendarAdapter : CalendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManagerR : RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView.layoutManager = layoutManagerR
        calendarRecyclerView.adapter = calendarAdapter
    }

    fun monthYearFromDate(date: LocalDate) : String
    {
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    fun previousMonthAction()
    {
        selectedDate = selectedDate.minusMonths(1)
        setMonthsView()
    }

    fun nextMonthAction()
    {
        selectedDate = selectedDate.plusMonths(1)
        setMonthsView()
    }

    fun daysInMonthArray(date : LocalDate) : ArrayList<String>
    {
        val daysInMonthArray : ArrayList<String> = ArrayList()
        val yearMonth : YearMonth = YearMonth.from(date)

        val daysInMonth : Int = yearMonth.lengthOfMonth()

        val firstOfMonth : LocalDate = selectedDate.withDayOfMonth(1)
        val dayOfWeek : Int = firstOfMonth.dayOfWeek.value - 1

        for(i in 1 .. 42)
        {
            if(i <= dayOfWeek || i >= (daysInMonth + dayOfWeek + 1))
            {
                daysInMonthArray.add("")
            }

            else
            {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    override fun onItemClick(position: Int, dayText: String) {
        if(dayText.equals(""))
        {
            Log.d("@@@", "!!!")
            val message : String = "Selected date " + dayText + " " + monthYearFromDate(selectedDate)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}