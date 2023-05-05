package com.example.findmydocpharm

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnLogin: Button
    lateinit var progressDialog: ProgressDialog
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.mbtnLogin)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        mAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            var email = edtEmail.text.toString().trim()
            var password = edtPassword.text.toString().trim()
            if (email.isEmpty() or password.isEmpty()){
                Toast.makeText(this,"Please fill all texts", Toast.LENGTH_LONG).show()
            }else{
                progressDialog.show()
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this,"Logged in successfully", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"User login failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}