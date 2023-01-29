package com.gabriela.motivationapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gabriela.motivationapp.infra.MotivationConstants
import com.gabriela.motivationapp.R
import com.gabriela.motivationapp.infra.SecurityPreferences
import com.gabriela.motivationapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() // A supportActionBar é a barra de navegação
        binding.button.setOnClickListener(this)

        handleUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button) generateMotivationPhrase()
    }

    private fun generateMotivationPhrase() : String = ""

    private fun handleUserName() {
        // Recuperando o nome do usuário
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.title.text = "Ola, $name!"
    }
}