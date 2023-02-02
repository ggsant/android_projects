package com.gabriela.motivationapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gabriela.motivationapp.infra.MotivationConstants
import com.gabriela.motivationapp.R
import com.gabriela.motivationapp.data.MockPhrases
import com.gabriela.motivationapp.infra.SecurityPreferences
import com.gabriela.motivationapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleFilter(R.id.ic_all_inclusive)
        handleNextPhrase(categoryId)
        supportActionBar?.hide() // A supportActionBar é a barra de navegação

        binding.button.setOnClickListener(this)
        binding.icAllInclusive.setOnClickListener(this)
        binding.icHappy.setOnClickListener(this)
        binding.icSunny.setOnClickListener(this)

        handleUserName()
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.button ->  handleNextPhrase(categoryId)
            R.id.ic_all_inclusive, R.id.ic_happy, R.id.ic_sunny -> handleFilter(view.id)
        }
    }

    private fun handleFilter(id: Int) {
        setImageViewColorFilter(binding.icAllInclusive, R.color.dark_purple)
        setImageViewColorFilter(binding.icSunny, R.color.dark_purple)
        setImageViewColorFilter(binding.icHappy, R.color.dark_purple)

        when(id) {
            R.id.ic_all_inclusive -> {
                setImageViewColorFilter(binding.icAllInclusive, R.color.white)
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.ic_sunny  -> {
                setImageViewColorFilter(binding.icSunny, R.color.white)
                categoryId = MotivationConstants.FILTER.SUNNY
            }
            R.id.ic_happy -> {
                setImageViewColorFilter(binding.icHappy, R.color.white)
                categoryId = MotivationConstants.FILTER.HAPPY
            }
        }
    }

    private fun setImageViewColorFilter(imageView: ImageView, color: Int) {
        imageView.setColorFilter(ContextCompat.getColor(this, color))
    }

    private fun handleNextPhrase(categoryId: Int) {
        binding.textPhrase.text = MockPhrases().getPhrase(categoryId)
    }

    private fun handleUserName() {
        // Recuperando o nome do usuário
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.title.text = "Ola, $name!"
    }
}