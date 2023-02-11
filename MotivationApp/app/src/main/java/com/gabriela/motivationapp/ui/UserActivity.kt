package com.gabriela.motivationapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gabriela.motivationapp.infra.MotivationConstants
import com.gabriela.motivationapp.R
import com.gabriela.motivationapp.infra.SecurityPreferences
import com.gabriela.motivationapp.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterButton.setOnClickListener(this)
        binding.createButton.setOnClickListener(this)

        supportActionBar?.hide()


    }


    private fun navigateToLogin() {
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun createAccount(email: String, password: String, name: String) {
        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
             SecurityPreferences(this).saveString(MotivationConstants.KEY.EMAIL, email)
             SecurityPreferences(this).saveString(MotivationConstants.KEY.PASSWORD, password)
             SecurityPreferences(this).saveString(MotivationConstants.KEY.USER_NAME, name)
            navigateToMainActivity()
        } else {
            Toast.makeText(this, R.string.error_empty_credentials, Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.enter_button -> navigateToLogin()
            R.id.create_button -> {
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()
                val name = binding.nameEditText.text.toString()
                createAccount(email, password, name)
            }
        }
    }
}

