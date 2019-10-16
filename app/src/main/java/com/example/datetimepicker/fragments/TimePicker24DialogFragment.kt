package com.example.datetimepicker.fragments


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

import com.example.datetimepicker.R
import kotlinx.android.synthetic.main.fragment_time_picker24_dialog.view.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("IMPLICIT_CAST_TO_ANY")
class TimePicker24DialogFragment : AppCompatDialogFragment() {

        private var mHourOfDay: String = ""
        private lateinit var dialogView: View
        private lateinit var alertDialogTimePicker24: AlertDialog
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
            view = inflater.inflate(R.layout.fragment_time_picker24_dialog, container, false)

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
                activity!!.layoutInflater.inflate(R.layout.fragment_time_picker24_dialog, null)
            dialogBuilder.setView(dialogView)

            val timePicker = dialogView.timePicker24
            timePicker.setIs24HourView(true)
            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                val selectedHourOfDay = if (hourOfDay < 10) "0$hourOfDay" else hourOfDay
                val selectedMinute = if (minute < 10) "0$minute" else minute

                mHourOfDay = "Hora: $selectedHourOfDay : $selectedMinute "
            }


            dialogView.btOk.setOnClickListener {
                Toast.makeText(mContext, mHourOfDay, Toast.LENGTH_LONG).show()
                dialog?.dismiss()
            }

            dialogView.btCancel.setOnClickListener {
                dialog?.dismiss()
            }

            alertDialogTimePicker24 = dialogBuilder.create()

            return alertDialogTimePicker24
        }


}
