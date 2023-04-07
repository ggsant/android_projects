package com.gabriela.coffe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.gabriela.coffe.R
import com.gabriela.coffe.databinding.ActivityHomeBinding
import com.gabriela.coffe.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.drink01.setOnClickListener(this)

        supportActionBar?.title = "Cocktail"
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title?.let {
            val titleColor = ContextCompat.getColor(this, R.color.dark_purple)
            val spannableString = SpannableString(it)
            spannableString.setSpan(ForegroundColorSpan(titleColor), 0, it.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            supportActionBar?.title = spannableString
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    private fun onCardClicked(view: View) {
        Toast.makeText(this, "O CardView foi clicado", Toast.LENGTH_SHORT).show()
    }

    private fun openDetailsScreen() {
        val intent = Intent(this, DrinkDetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_button -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.drink01 -> openDetailsScreen()
            R.id.drink02 -> openDetailsScreen()
            R.id.drink03 -> openDetailsScreen()
            R.id.drink04 -> openDetailsScreen()
            R.id.drink05 -> openDetailsScreen()
            R.id.drink06 -> openDetailsScreen()
        }
    }
}