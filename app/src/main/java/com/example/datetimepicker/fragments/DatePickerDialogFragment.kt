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
import kotlinx.android.synthetic.main.fragment_date_picker_dialog.view.*
import java.util.*

@Suppress("IMPLICIT_CAST_TO_ANY")
class DatePickerDialogFragment : AppCompatDialogFragment() {

    private var dateFormatted: String = ""
    private var date: String = ""
    private lateinit var dialogView: View
    private lateinit var alertDialogDatePicker: AlertDialog
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_date_picker_dialog, container, false)

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
            activity!!.layoutInflater.inflate(R.layout.fragment_date_picker_dialog, null)
        dialogBuilder.setView(dialogView)

        val datePicker = dialogView.datePiker
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()

        datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ) { datePicker, year, month, dayOfMonth ->

            val mDay =  if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth
            val mMonth =  if ((month + 1) < 10) "0$(month + 1)h" else (month + 1)

            dateFormatted = "Date: $mDay/$mMonth/$year"
            date = "$dateFormatted \nday: $mDay \nMonth: $mMonth \nYear: $year"

            val myCal = Calendar.getInstance()
            myCal.set(Calendar.YEAR, year)
            myCal.set(Calendar.MONTH, month)
            myCal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val timeStampFormatter = TimeStampFormatter()
            val dateAgo = timeStampFormatter.format(myCal.time)

            Toast.makeText(mContext, dateAgo, Toast.LENGTH_LONG).show()
        }

        dialogView.btOk.setOnClickListener {
            Toast.makeText(mContext, date, Toast.LENGTH_LONG).show()
            dialog?.dismiss()
        }

        dialogView.btCancel.setOnClickListener {
            dialog?.dismiss()
        }

        alertDialogDatePicker = dialogBuilder.create()

        return alertDialogDatePicker
    }

}
