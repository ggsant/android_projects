package com.gabriela.coffe.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.gabriela.coffe.R
import com.gabriela.coffe.databinding.ActivityCreateAccountBinding
import com.gabriela.coffe.infra.Cryptography
import com.gabriela.motivationapp.infra.MotivationConstants
import com.gabriela.coffe.infra.SecurityPreferences

class CreateAccountActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding : ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Criar conta"
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title?.let {
            val titleColor = ContextCompat.getColor(this, R.color.dark_purple)
            val spannableString = SpannableString(it)
            spannableString.setSpan(ForegroundColorSpan(titleColor), 0, it.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            supportActionBar?.title = spannableString
        }

        setTheme(R.style.Theme_Coffe)

        binding.createAccountButton.setOnClickListener(this)
        binding.nameEditText.setOnClickListener(this)
        binding.emailEditText.setOnClickListener(this)
        binding.passwordEditText.setOnClickListener(this)

    }

    private fun handleError(message: String?) {
        val errorMessage = message ?: resources.getString(R.string.error_generic)
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View) {
       when(view.id) {
           R.id.create_account_button -> {
               val name = binding.nameEditText.text.toString()
               val email = binding.emailEditText.text.toString()
               val password = binding.passwordEditText.text.toString()
               val passwordEncrypted =  Cryptography().encryptString(password)
               createAccount(name,email, passwordEncrypted)
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun createAccount(name: String, email: String, password: String) {
        try {
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                SecurityPreferences(this).saveString(MotivationConstants.KEY.USER_NAME, name)
                SecurityPreferences(this).saveString(MotivationConstants.KEY.EMAIL, email)
                SecurityPreferences(this).saveString(MotivationConstants.KEY.PASSWORD, password)
                SecurityPreferences(this).saveBool(MotivationConstants.KEY.IS_LOGGED, true)
                openHomeScreen()
            } else {
                val message = resources.getString(R.string.error_empty_credentials)
                handleError(message)
            }
        } catch (error: Exception) {
            val message = resources.getString(R.string.error_create_account)
            handleError(message)
        }
    }
}