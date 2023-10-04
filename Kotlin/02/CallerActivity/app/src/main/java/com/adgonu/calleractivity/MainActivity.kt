package com.adgonu.calleractivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.adgonu.calleractivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var myResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            binding.tvResultado.text = data?.getStringExtra(Intent.EXTRA_RETURN_RESULT)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btPassar.setOnClickListener{sendCalc()}

        binding.cbSuma.setOnClickListener(){
            binding.cbDividir.isChecked = false
            binding.cbMultiplicar.isChecked = false
            binding.cbResta.isChecked = false
        }
        binding.cbResta.setOnClickListener(){
            binding.cbDividir.isChecked = false
            binding.cbMultiplicar.isChecked = false
            binding.cbSuma.isChecked = false
        }
        binding.cbDividir.setOnClickListener(){
            binding.cbResta.isChecked = false
            binding.cbMultiplicar.isChecked = false
            binding.cbSuma.isChecked = false
        }
        binding.cbMultiplicar.setOnClickListener(){
            binding.cbDividir.isChecked = false
            binding.cbResta.isChecked = false
            binding.cbSuma.isChecked = false
        }


    }

    private fun sendCalc(){

        var primerNumero: String = binding.etPrimerNumero.text.toString()
        var segundoNumero: String = binding.etSegundoNumero.text.toString()
        var signo: String = ""

        if(binding.cbSuma.isChecked){
            signo = "+"
        }
        else if(binding.cbResta.isChecked){
            signo = "-"
        }
        else if(binding.cbDividir.isChecked){
            signo = "/"
        }
        else if(binding.cbMultiplicar.isChecked){
            signo = "*"
        }

        val SendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, primerNumero +" "+ signo +" "+ segundoNumero)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser( SendIntent,"Escoge la aplicacion")
        myResultLauncher.launch(shareIntent)
    }
}