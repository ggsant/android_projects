package com.gabriela.coffe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gabriela.coffe.R
import com.gabriela.coffe.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding : ActivityCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
       when(view.id) {
           R.id.enter_button -> openHomeScreen()
       }
    }

    private fun openHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}