package com.gabriela.coffe.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gabriela.coffe.R
import com.gabriela.coffe.databinding.ActivityMainBinding
import com.gabriela.motivationapp.infra.MotivationConstants
import com.gabriela.motivationapp.infra.SecurityPreferences

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleIsLogged()

        supportActionBar?.hide()

        binding.createAccountButton.setOnClickListener(this)
        binding.enterButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.create_account_button -> openCreateAccountScreen()
            R.id.enter_button -> openLoginScreen()
        }
    }

    private fun openCreateAccountScreen() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }

    private fun openLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun handleIsLogged() {
        val isLogged = SecurityPreferences(this).getBool(MotivationConstants.KEY.IS_LOGGED)
        if (isLogged) openHomeScreen()
    }

    private fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}