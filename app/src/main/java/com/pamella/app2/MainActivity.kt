package com.pamella.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.time.ExperimentalTime
import android.app.DatePickerDialog
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*


class MainActivity : AppCompatActivity() {

    var txtnome: EditText? = null
    lateinit var txtdata: EditText
    var txtpresente: EditText? = null
    lateinit var bot: Button
    lateinit var frase: TextView
    val lista = mutableListOf<String>()


    var datepicker: DatePickerDialog? = null

    @RequiresApi(Build.VERSION_CODES.O)

    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtnome = findViewById(R.id.nome)
        txtdata = findViewById(R.id.nascimento)
        txtpresente = findViewById(R.id.presente)
        bot = findViewById(R.id.botao)
        frase = findViewById(R.id.resultado)

        var d = 0
        var m = 0
        var a = 0

        // quando clicar na data, aparece o calendario
        txtdata.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val ano: Int = cldr.get(Calendar.YEAR)
            val mes: Int = cldr.get(Calendar.MONTH)
            val dia: Int = cldr.get(Calendar.DAY_OF_MONTH)

            datepicker = DatePickerDialog(
                this@MainActivity,
                { view, year, monthOfYear, dayOfMonth ->
                    d = dayOfMonth
                    m = monthOfYear + 1
                    a = ano

                    txtdata.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + ano)
                },
                ano,
                mes,
                dia
            )
            datepicker!!.show()
        }

        //Executa quando aperta o botao
        bot.setOnClickListener() {
            val nome = txtnome?.text.toString()
            val pre = txtpresente?.text.toString()

            // fazer calculo da data
            val datadig = LocalDate.of(a,m,d)
            val datanow = LocalDate.now()
            val diasf = ChronoUnit.DAYS.between(datanow,datadig)
            //Saida
            lista.add(" Nome: $nome.\n Dias para próximo aniversário: $diasf.\n Presente ideal: $pre.\n") //Add os elementos a lista
            frase.visibility= View.VISIBLE //Deixa o txtview visivel novamente
            // usa um for para tirar os elementos da lista e não aparecer as chaves
            var exibirp :String = ""
            for (i in lista){
                exibirp += "$i \n"
           }
            frase.text = exibirp  //Exibe os a variável onde foi colocado os elementos da lista
        }

    }
}