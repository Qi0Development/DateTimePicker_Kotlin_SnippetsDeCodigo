package com.example.datetimepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.example.datetimepicker.fragments.DatePickerDialogFragment
import com.example.datetimepicker.fragments.TimePicker24DialogFragment
import com.example.datetimepicker.fragments.TimePickerDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btDatePicker.setOnClickListener { showDatePicker() }
        btTimePicker.setOnClickListener { showTimePicker() }
        btTimePicker24.setOnClickListener { showTimePicker24() }
        btDateTimePicker.setOnClickListener {  }
    }

    private fun showDatePicker() {
        val dialogFragment = DatePickerDialogFragment()
        val fm = supportFragmentManager
        dialogFragment.show(fm, "DatePickerDialogFragment")
    }

    private fun showTimePicker() {
        val dialogFragment = TimePickerDialogFragment()
        val fm = supportFragmentManager
        dialogFragment.show(fm, "TimePickerDialogFragment")
    }

    private fun showTimePicker24() {
        val dialogFragment = TimePicker24DialogFragment()
        val fm = supportFragmentManager
        dialogFragment.show(fm, "TimePicker24DialogFragment")
    }
}
