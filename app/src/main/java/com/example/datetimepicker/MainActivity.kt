package com.example.datetimepicker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.datetimepicker.fragments.DatePickerDialogFragment
import com.example.datetimepicker.fragments.TimePicker24DialogFragment
import com.example.datetimepicker.fragments.TimePickerDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btDatePicker.setOnClickListener { showDatePicker() }
        btTimePicker.setOnClickListener { showTimePicker() }
        btTimePicker24.setOnClickListener { showTimePicker24() }
        showDates()
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

    /*@RequiresApi(Build.VERSION_CODES.O)
    private fun showDates() {
        // Cria um Objeto LocalDate com a data atual.
        val hoje = LocalDate.now()

        // Cria um Objeto LocalDate com a data 26/09/2020.
        val outraData = LocalDate.of(2018, Month.OCTOBER, 1)

        // Calcula a diferença de dias entre as duas datas
        val diferencaEmDias = ChronoUnit.DAYS.between(hoje, outraData)
        // Calcula a diferença de meses entre as duas datas
        val diferencaEmMes = ChronoUnit.MONTHS.between(hoje, outraData)
        // Calcula a diferença de anos entre as duas datas
        val diferencaEmAnos = ChronoUnit.YEARS.between(hoje, outraData)

        // Exibe a diferença em dias entre as datas
        Log.d("TAG","Diferença em dias entre $hoje e $outraData = $diferencaEmDias")

        // Exibe a diferença em meses entre as datas
        Log.d("TAG","Diferença em meses entre $hoje e $outraData = $diferencaEmMes")
        // Exibe a diferença em anos entre as datas
        Log.d("TAG","Diferença em anos entre $hoje e $outraData = $diferencaEmAnos")
    }*/

    /*private fun showDates() {


        val dataCadastro = Calendar.getInstance()
        dataCadastro.set(2018, 0, 1)
        val hoje = Calendar.getInstance()

        //calcula diferença
        val meses =
            hoje.get(Calendar.YEAR) * 12 + hoje.get(Calendar.MONTH) - (dataCadastro.get(Calendar.YEAR) * 12 + dataCadastro.get(
                Calendar.MONTH
            ))

        // Exibe a diferença em dias entre as datas
        Log.d("TAG", "Diferença de Messes " + (meses +1))

    }*/

    private fun showDates() {

        val c1 = Calendar.getInstance()
        c1.set(Calendar.DAY_OF_MONTH, 1)
        c1.set(Calendar.MONTH, 8)
        c1.set(Calendar.YEAR, 2018)

        val c2 = Calendar.getInstance()

        val diferenca = c2.time.time - c1.time.time

        // Exibe a diferença em dias entre as datas
        Log.d("TAG", "Diferença de Dias " + TimeUnit.MILLISECONDS.toDays(diferenca))


        when {
            diferenca in 1..30 -> Log.d("TAG", "Ultima Visita há " + TimeUnit.MILLISECONDS.toDays(diferenca) +" Dias")
            diferenca in 30..365 -> Log.d("TAG", "Ultima Visita há " + TimeUnit.MILLISECONDS.toDays(diferenca)/12 +" Meses")
            diferenca > 365 -> Log.d("TAG", "Ultima Visita há " + TimeUnit.MILLISECONDS.toDays(diferenca)/365 +" Anos")
        }

    }
}
