package com.gabriela.coffe.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gabriela.coffe.R
import com.gabriela.coffe.databinding.ActivityLoginBinding
import com.gabriela.motivationapp.infra.MotivationConstants
import com.gabriela.coffe.infra.SecurityPreferences

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Login"

        setTheme(R.style.Theme_Coffe)

        binding.enterButton.setOnClickListener(this)
    }

    private fun handleError(message: String?) {
        val errorMessage = message ?: resources.getString(R.string.error_generic)
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.enter_button -> {
                val email = binding.emailEditText.text.toString()
                val password = binding.emailEditText.text.toString()
                login(email, password)
            }
            else -> handleError(null)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


    private fun login(email: String, password: String) {
        try {
            verifyAccount(email, password)
        } catch (error: Exception) {
            handleError(error.message)
        }
    }

    private fun verifyAccount(email: String, password: String) {
        val emailSaved = SecurityPreferences(this).getString(MotivationConstants.KEY.EMAIL)
        val passwordSaved = SecurityPreferences(this).getString(MotivationConstants.KEY.PASSWORD)

        if (emailSaved.isNotEmpty() && passwordSaved.isNotEmpty()) {
            verifyEmailAndPassword(email, emailSaved, password, passwordSaved)
        } else {
            val isEmailOrPasswordEmpty = email.isEmpty() || password.isEmpty()
            val accountNotFoundMessage = resources.getString(R.string.error_account_not_found)
            val emptyCredentialsError = resources.getString(R.string.error_empty_credentials)
            val message =
                if (isEmailOrPasswordEmpty) emptyCredentialsError else  accountNotFoundMessage
            throw Exception(message)
        }
    }

    private fun verifyEmailAndPassword(
        email: String,
        emailSaved: String,
        password: String,
        passwordSaved: String,
    ) {
        if (emailSaved == email && passwordSaved == password) {
            SecurityPreferences(this).saveBool(MotivationConstants.KEY.IS_LOGGED, true)
            openHomeScreen()
        } else {
            val message = resources.getString(R.string.error_login_credentials)
            throw Exception(message)
        }
    }
}