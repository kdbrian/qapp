package com.star.qapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class signup : AppCompatActivity() {

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var password_confirm: TextInputEditText


    private lateinit var register_btn: Button

    private lateinit var login_prompt: TextView

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

//        initialize variables
        email = findViewById(R.id.sign_up_email)
        password = findViewById(R.id.sign_up_password)
        password_confirm = findViewById(R.id.sign_up_password_confirm)

        register_btn = findViewById(R.id.registerButton)
        login_prompt = findViewById(R.id.login_prompt)

        firebaseAuth = FirebaseAuth.getInstance()

//        register functionality
        register_btn.setOnClickListener { v ->
//            check the fields

            val mail = email.text.toString()
            val pwd = password.text.toString()
            val pwdc = password_confirm.text.toString()

            if (mail.isEmpty() || pwd.isEmpty() || pwdc.isEmpty())
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            else {

//                check password == password confirmation
                if (pwd != pwdc)
                    Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show()
                else {

                    firebaseAuth.createUserWithEmailAndPassword(mail, pwd)
                        .addOnCompleteListener { task: Task<AuthResult> ->

                            if (task.isSuccessful) {
                                print("$mail , $pwd")
//                            send verification email
                                //get the current user
                                sendVerificationMail()
                                /*
                            if(firebaseUser == null){
                                Toast.makeText(
                                    this@signup,
                                    "Failed to create account\n Check internet and try again or maybe the email\n is already taken",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }else{

                                firebaseUser.sendEmailVerification().addOnCompleteListener { task: Task<Void> ->

                                    if (task.isSuccessful){
                                        Toast.makeText(
                                            applicationContext,
                                            "Verify your email to login",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        //signing out user to let them login again
                                        FirebaseAuth.getInstance().signOut()
                                        finish()
                                        startActivity(Intent(this@signup, MainActivity::class.java))
                                    }else
                                        Toast.makeText(
                                            applicationContext,
                                            "Failed to send email",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                }


                            }

                        }
                        */
                            }
                        }
                }

            }

//        take user back to login screen
            login_prompt.setOnClickListener { v ->
                startActivity(Intent(this, MainActivity::class.java))
            }

        }


    }
    fun sendVerificationMail() {
        //getting the current user
        val user = firebaseAuth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener {
            Toast.makeText(applicationContext, "Verify your email to login", Toast.LENGTH_SHORT)
                .show()
            //signing out user to let them login again
            FirebaseAuth.getInstance().signOut()
            finish()
            startActivity(Intent(this@signup, MainActivity::class.java))
        }
            ?: Toast.makeText(
                this@signup,
                "Failed to send verification email\n Check internet and try again",
                Toast.LENGTH_SHORT
            ).show()
    }
}