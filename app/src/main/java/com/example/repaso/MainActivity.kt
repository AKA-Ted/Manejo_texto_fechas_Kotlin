package com.example.repaso

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Period
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val LOCALE = Locale("es","MX")
    private val fmtFecha = SimpleDateFormat("dd/MM/yyyy", LOCALE)
    private val fmtHora = SimpleDateFormat("HH:mm", LOCALE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        procesar.setOnClickListener(this)
    }


    override fun onClick(v:View?){
        val bol = boleta.text.toString()
        if(TextUtils.isEmpty(bol)) boleta.error = "Ingresa algo"
        else salidaBoleta.text = bol

        val nom = nombre.text.toString()
        if(TextUtils.isEmpty(nom)) nombre.error = "Ingresa algo"
        else salidaNombre.text = nom

        val grup = grupo.text.toString()
        if(TextUtils.isEmpty(bol)) grupo.error = "Ingresa algo"
        else salidaGrupo.text = grup


        val tPicker = Calendar.getInstance()
        tPicker.set(Calendar.HOUR, hora.hour)
        tPicker.set(Calendar.MINUTE, hora.minute)
        salidaHora.text = fmtHora.format(tPicker.time)


        val dPicker = Calendar.getInstance()
        val dia = fecha.dayOfMonth
        val mes = fecha.month
        val año = fecha.year
        dPicker.set(Calendar.DAY_OF_MONTH, dia)
        dPicker.set(Calendar.MONTH, mes)
        dPicker.set(Calendar.YEAR, año)
        salidaFecha.text = fmtFecha.format(dPicker.time)

        calcular_edad(dPicker)


    }

    fun calcular_edad(dPicker: Calendar) {
        val cal = Calendar.getInstance()
        val anterior = dPicker

        var year_diff = cal.get(Calendar.YEAR) - anterior.get(Calendar.YEAR)
        val month_diff = cal.get(Calendar.MONTH) - anterior.get(Calendar.MONTH)
        val day_diff = cal.get(Calendar.DAY_OF_MONTH) - anterior.get(Calendar.DAY_OF_MONTH)

        if(month_diff < 0 || (month_diff == 0 && day_diff < 0) || day_diff < 0){
            year_diff--
        }

        Toast.makeText(this, "Tienes $year_diff años ", Toast.LENGTH_LONG).show()
    }
}