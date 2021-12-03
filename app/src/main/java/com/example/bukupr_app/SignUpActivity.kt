package com.example.bukupr_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var pbSignUp: ProgressBar
    private lateinit var ivSignUpLogo: ImageView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        pbSignUp = findViewById(R.id.pbSignUp)
        ivSignUpLogo = findViewById(R.id.ivSignUpLogo)
        pbSignUp = findViewById(R.id.pbSignUp)

        btnSignUp.setOnClickListener(this)
        ivSignUpLogo.setOnClickListener(this)

        // Initialize Firebase Auth
        auth = Firebase.auth

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivSignUpLogo ->
                startActivity(Intent(this, LoginActivity::class.java))
            R.id.btnSignUp ->
                signUp()
        }
    }

    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun signUp() {
        val fname = etFirstName.text.toString().trim()
        val lname = etLastName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if(fname.isEmpty()){
            etFirstName.setError("First Name is required!")
            etFirstName.requestFocus()
        }

        if(lname.isEmpty()){
            etLastName.setError("Last Name is required!")
            etLastName.requestFocus()
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please provide valid email!")
            etEmail.requestFocus()
        }

        if(password.isEmpty()){
            etPassword.setError("Password is required!")
            etPassword.requestFocus()
        }

    }

}