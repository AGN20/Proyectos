package com.adgonu.receiveractivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.adgonu.receiveractivity.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

var resultado = ""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = intent
        var text:String? = null

        data?.let{
            if(data.hasExtra(Intent.EXTRA_TEXT)){
                text = data.getStringExtra(Intent.EXTRA_TEXT).toString()
                var numero: List<String> = text!!.split(" ")

                binding.tv1Numero.text = numero[0]
                binding.tvSigno.text = numero[1]
                binding.tv2Numero.text = numero[2]

            }
        }

        binding.btCalcular.setOnClickListener{

            var primerNumero = binding.tv1Numero.text.toString().toInt()
            var segundoNumero = binding.tv2Numero.text.toString().toInt()
            var signo = binding.tvSigno.text.toString()

            if(signo == "+"){
                resultado = (primerNumero + segundoNumero).toString()
            }
            else if(signo == "-"){
                resultado = (primerNumero - segundoNumero).toString()
            }
            else if(signo == "/"){
                resultado = (primerNumero / segundoNumero).toString()
            }
            else if(signo == "*"){
                resultado = (primerNumero * segundoNumero).toString()
            }



            val intent = Intent().apply {
                putExtra(Intent.EXTRA_RETURN_RESULT, resultado?:"No text")
            }

            setResult(RESULT_OK, intent)
            finish()
        }

    }
}