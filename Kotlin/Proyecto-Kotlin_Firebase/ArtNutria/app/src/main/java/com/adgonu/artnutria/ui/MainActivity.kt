package com.adgonu.artnutria.ui

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adgonu.artnutria.R
import com.adgonu.artnutria.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Matcher
import java.util.regex.Pattern

//SHA1: FF:4A:C3:38:97:26:3F:B1:03:80:76:F0:B4:8F:A7:0A:07:4F:91:6D

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(3000)
        setTheme(R.style.Theme_ArtNutria)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        var view = View(this)

        if(FirebaseAuth.getInstance().currentUser != null){
            changeActivity()
        }else{
            checkPermissions()
            setup(view)
        }
    }

    private fun setup(view: View) {
        //Iniciamos los elementos del layaut
        val btRegistrar: Button = binding.bttRegistrar
        val btLoguin: Button = binding.bttLogin
        val btEntrar: Button = binding.bttEntrar
        val edEmail: EditText = binding.edtEmail
        val edPassword: EditText = binding.edtPassword
        val progressBar: ProgressBar = binding.progressBar

        var email = edEmail.text
        var password = edPassword.text

        /** REGISTRAR **/
        btRegistrar.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.toString(), password.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        progressBar.visibility = View.GONE
                        changeActivity()
                    }else{
                        progressBar.visibility = View.GONE
                        if(password.toString().length < 6){ showAlertPassword() }
                        if(!validarEmail(email.toString())){ showAlertEmail() }
                    }
                }.addOnFailureListener {
                    progressBar.visibility = View.GONE
                    showAlertUser()
                }
            }else{
                progressBar.visibility = View.GONE
                showAlertNotEmty()
            }
        }

        /** LOGUIN **/
        btLoguin.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toString(), password.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        progressBar.visibility = View.GONE
                        changeActivity()
                    }else{
                        progressBar.visibility = View.GONE
                        showAlertEntriFailed()
                    }
                }
            }else{
                progressBar.visibility = View.GONE
                showAlertNotEmty()
            }
        }

        /** ENTRAR **/
        btEntrar.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            FirebaseAuth.getInstance().signInWithEmailAndPassword(this.getString(R.string.NotUser), this.getString(R.string.NotUserPassword)).addOnCompleteListener {
                if(it.isSuccessful){
                    progressBar.visibility = View.GONE
                    changeActivity()
                }else{
                    progressBar.visibility = View.GONE
                    showAlertEntriFailed()
                }
            }
        }
    }

    /** El usuario ya ha sido creado **/
    private fun showAlertUser() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(this.getString(R.string.TituloError))
        builder.setMessage(this.getString(R.string.UsuarioCreado))
        builder.setPositiveButton(this.getString(R.string.Aceptar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /** mensaje FALLO AL AUTENTIFICAR **/
    private fun showAlertEntriFailed(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(this.getString(R.string.TituloError))
        builder.setMessage(this.getString(R.string.ErrorEmailContraseña))
        builder.setPositiveButton(this.getString(R.string.Aceptar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /** mensaje FALLO el password no es correcto **/
    private fun showAlertPassword(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(this.getString(R.string.TituloError))
        builder.setMessage(this.getString(R.string.ErrorCaracteres))
        builder.setPositiveButton(this.getString(R.string.Aceptar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /** mensaje FALLO el email no es correcto **/
    private fun showAlertEmail(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(this.getString(R.string.TituloError))
        builder.setMessage(this.getString(R.string.ErrorFormatoGmail))
        builder.setPositiveButton(this.getString(R.string.Aceptar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /** mensaje FALLO si uno de los elementos esta vacio **/
    private fun showAlertNotEmty(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(this.getString(R.string.TituloError))
        builder.setMessage(this.getString(R.string.ErrorVacio))
        builder.setPositiveButton(this.getString(R.string.Aceptar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /** VALIDAR EL EMAIL **/
    private fun validarEmail(texto: String): Boolean {
        val patern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        var comparator: Matcher = patern.matcher(texto)
        return comparator.find()
    }

    /** CAMBIAR EL FRAGMENTO **/
    fun changeActivity(){
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    /** Cambio de la funcion volber atras **/
    override fun onBackPressed() {
        finishAffinity()
    }

    /** Compruba que tengamos el permiso de notificaciones activo **/
    private fun checkPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            requestNotificationPermission()
        }
    }

    /** Pide los permisos necesarios **/
    private fun requestNotificationPermission() {
        if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
        }
    }
}