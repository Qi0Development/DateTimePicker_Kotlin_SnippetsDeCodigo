package com.example.datetimepicker.fragments


import TimeStampFormatter
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

import com.example.datetimepicker.R
import kotlinx.android.synthetic.main.fragment_time_picker_dialog.view.*
import java.util.*

@Suppress("IMPLICIT_CAST_TO_ANY")
class TimePickerDialogFragment : AppCompatDialogFragment() {

    private var mHourOfDay: String = ""
    private lateinit var dialogView: View
    private lateinit var alertDialogTimePickern: AlertDialog
    internal lateinit var view: View
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_time_picker_dialog, container, false)

        dialog?.let {
            dialog?.window.let {
                it?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it?.requestFeature(Window.FEATURE_NO_TITLE)
            }
        }
        return view
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(mContext)

        dialogView =
            activity!!.layoutInflater.inflate(R.layout.fragment_time_picker_dialog, null)
        dialogBuilder.setView(dialogView)

        val timePicker = dialogView.timePicker
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            var selectedHourOfDay = hourOfDay
            val selectedMinute = minute

            var format = ""
            when {
                hourOfDay == 0 -> {
                    selectedHourOfDay += 12
                    format = "AM"
                }
                hourOfDay == 12 -> format = "PM"
                hourOfDay > 12 -> {
                    selectedHourOfDay -= 12
                    format = "PM"
                }
                else -> format = "AM"
            }

            val hour = if (selectedHourOfDay < 10) "0$selectedHourOfDay" else selectedHourOfDay
            val min = if (selectedMinute < 10) "0$selectedMinute" else selectedMinute

            mHourOfDay = "Hora: $hour : $min $format"

            val myCal = Calendar.getInstance()
            myCal.set(Calendar.HOUR_OF_DAY, hourOfDay)
            myCal.set(Calendar.MINUTE, minute)

            val timeStampFormatter = TimeStampFormatter()
            val dateAgo = timeStampFormatter.format(myCal.time)
            Toast.makeText(mContext, dateAgo, Toast.LENGTH_LONG).show()
        }


        dialogView.btOk.setOnClickListener {
            Toast.makeText(mContext, mHourOfDay, Toast.LENGTH_LONG).show()
            dialog?.dismiss()
        }

        dialogView.btCancel.setOnClickListener {
            dialog?.dismiss()
        }

        alertDialogTimePickern = dialogBuilder.create()

        return alertDialogTimePickern
    }

}
