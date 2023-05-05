package com.example.findmydocpharm

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    lateinit var mTvRegister:TextView
    lateinit var edtName:EditText
    lateinit var edtEmail:EditText
    lateinit var edtDob:EditText
    lateinit var edtPassword:EditText
    lateinit var btnReg:Button
    lateinit var progressDialog: ProgressDialog
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtDob = findViewById(R.id.edtDob)
        edtPassword = findViewById(R.id.edtPassword)
        btnReg = findViewById(R.id.btnReg)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        mAuth = FirebaseAuth.getInstance()
        btnReg.setOnClickListener {
            var name = edtName.text.toString().trim()
            var email = edtEmail.text.toString().trim()
            var dob = edtDob.text.toString().trim()
            var password = edtPassword.text.toString().trim()
            var id = System.currentTimeMillis().toString()
            if (name.isEmpty() or email.isEmpty() or dob.isEmpty() or password.isEmpty()){
                Toast.makeText(this,"Please fill all texts",Toast.LENGTH_LONG).show()
            }else{
                progressDialog.show()
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        var userData = User(name,email,dob,password,mAuth.currentUser!!.uid,id)
                        var ref = FirebaseDatabase.getInstance().reference.child("Users/"+id)
                        ref.setValue(userData)
                        Toast.makeText(this,"User registered successfully",Toast.LENGTH_LONG).show()
                        mAuth.signOut()
                        startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"User registration failed",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}