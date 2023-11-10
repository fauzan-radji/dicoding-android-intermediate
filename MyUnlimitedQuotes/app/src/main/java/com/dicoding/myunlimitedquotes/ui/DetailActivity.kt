package com.dicoding.myunlimitedquotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.myunlimitedquotes.R
import com.dicoding.myunlimitedquotes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val quote = intent.getStringExtra(EXTRA_QUOTE)
        binding.tvDetail.text = quote
    }

    companion object {
        const val EXTRA_QUOTE = "extra_quote"
    }
}