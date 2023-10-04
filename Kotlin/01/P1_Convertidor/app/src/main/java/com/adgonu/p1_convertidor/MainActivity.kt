package com.adgonu.p1_convertidor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.adgonu.p1_convertidor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btCalcular.setOnClickListener(){
            //Cogemos el estado del switch
            var eleccion = binding.swEleccion.isChecked

            //Si esta en false haremos la parte de BINARIO a DECIMAL si esta en true de DECIMAL a BINARIO
            if(eleccion == true){
                if(binding.edtDecimal.text.toString() != ""){
                    //De Decimal ---> Binario
                    var numero = binding.edtDecimal.text.toString()
                    var binario = canviarBinario(numero)

                    //Controlamos que no sea mas largo de 10 letras
                    if(binario.length > 10){
                        //binding.edtBinario.setText("Desbordado")
                        binding.edtBinario.setError("Desbordamiento")
                    }else{
                        binding.edtBinario.setError(null)
                        binding.edtBinario.setText(binario)
                    }
                }
            }else{
                if(binding.edtBinario.text.toString() != ""){
                    //De Binario ---> Decimal
                    var numero = binding.edtBinario.text.toString()
                    var decimal = canviarDecimal(numero)

                    //No hago la comprobacion, no es necesaria en este caso, el numero binario tendria que tener más de 10 caracteres para necesitar la condición
                    binding.edtDecimal.setText(decimal)

                }
            }
        }
    }
}

//Convertimos el numero Decimal a Binario
fun canviarBinario(numero : String): String{
    //No podemos pasarlo a Int, porque el numero es muy largo, pero si que lo podemos pasar a BigInteguer
    var n = numero.toBigInteger()
    //Sacamos el binario de n con .toBinaryString
    var binario = Integer.toBinaryString(n.toInt())

    return binario

}

//Convertimos el numero Binario a Decimal
fun canviarDecimal(numero : String): String{

    var n = numero.toBigInteger().toInt()
    var decimal = 0
    var i = 0
    var resto: Long

    //Esta es la formula que utilizamos para pasar de Binario a decimal
    while(n != 0){
        resto = n.toLong() % 10
        n /= 10
        decimal += (resto * Math.pow(2.0, i.toDouble())).toInt()
        ++i
    }

    return decimal.toString()
}