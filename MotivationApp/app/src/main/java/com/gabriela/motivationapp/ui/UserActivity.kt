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

        binding.saveButton.setOnClickListener(this)

        supportActionBar?.hide()

        verifyUserName()
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != "") {
            navigateToMainActivity()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.save_button) handleSave()
    }

    private fun handleSave() {
        val name = binding.textInput.text.toString()
        if (name != "") {
            print(name)
            SecurityPreferences(this).saveString(MotivationConstants.KEY.USER_NAME, name)
            navigateToMainActivity()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToMainActivity() {
        // Navegação -> Intent
        startActivity(Intent(this, MainActivity::class.java))
        finish() // destroi a activity depois de navegar
    }
}