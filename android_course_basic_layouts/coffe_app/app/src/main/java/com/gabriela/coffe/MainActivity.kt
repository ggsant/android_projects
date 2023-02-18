package com.gabriela.coffe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gabriela.coffe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        TODO("Not yet implemented")
    }

    private fun openLoginScreen() {
        TODO("Not yet implemented")
    }
}