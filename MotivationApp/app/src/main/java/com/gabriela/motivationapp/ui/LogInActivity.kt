package com.gabriela.motivationapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gabriela.motivationapp.R
import com.gabriela.motivationapp.databinding.ActivityLogInBinding
import com.gabriela.motivationapp.infra.MotivationConstants
import com.gabriela.motivationapp.infra.SecurityPreferences

class LogInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterButton.setOnClickListener(this)
        binding.createButton.setOnClickListener(this)

        supportActionBar?.hide()

        verifyUserLogged()
    }

    private fun verifyUserLogged() {
        val isUserLogged = SecurityPreferences(this).getBool(MotivationConstants.KEY.IS_LOGGED)
        if (isUserLogged) {
            navigateToMainActivity()
        }
    }

    private fun navigateToCreateAccount() {
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun handleLogIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            val emailSaved = SecurityPreferences(this).getString(MotivationConstants.KEY.EMAIL)
            val passwordSaved =
                SecurityPreferences(this).getString(MotivationConstants.KEY.PASSWORD)

            if (emailSaved.isNotEmpty() && passwordSaved.isNotEmpty()) {
                SecurityPreferences(this).saveBool(MotivationConstants.KEY.IS_LOGGED, true)
                navigateToMainActivity()
            } else {
                SecurityPreferences(this).saveBool(MotivationConstants.KEY.IS_LOGGED, false)
                Toast.makeText(this, R.string.error_login, Toast.LENGTH_LONG).show()
            }
        } else {
            SecurityPreferences(this).saveBool(MotivationConstants.KEY.IS_LOGGED, false)
            Toast.makeText(this, R.string.error_empty_credentials, Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.create_button -> navigateToCreateAccount()
            R.id.enter_button -> {
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()
                handleLogIn(email, password)
            }
        }
    }
}