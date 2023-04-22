package com.star.qapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var loginBtn : Button

    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText

    private lateinit var signupprompt : TextView

//    firebase stuff
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn= findViewById(R.id.loginbtn)

        email= findViewById(R.id.login_email)
        password= findViewById(R.id.login_password)

        signupprompt= findViewById(R.id.sign_up_prompt)

        firebaseAuth = FirebaseAuth.getInstance()

//        confirm if the user is not logged in
        if (firebaseAuth.currentUser != null){
//            if user is logged in load the filters page
            finish()
            startActivity(Intent(this, filtercontent::class.java))
        }

//        login logic
        loginBtn.setOnClickListener{v : View->

//            check email and password and sign in with email and password
            var emailtxt =  email.text.toString()
            var passtxt =  password.text.toString()
            if( emailtxt.isEmpty() || passtxt.isEmpty() ){
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }else{

                firebaseAuth.signInWithEmailAndPassword(emailtxt, passtxt)
                    .addOnCompleteListener { task: Task<AuthResult> ->

                        if(task.isSuccessful)
                            startActivity(Intent(this@MainActivity, filtercontent::class.java))
                        else
                            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
            }
        }
        signupprompt.setOnClickListener{v : View->
            startActivity(Intent(this, signup::class.java))
        }
    }
}