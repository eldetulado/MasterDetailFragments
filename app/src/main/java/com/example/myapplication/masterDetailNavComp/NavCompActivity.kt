package com.example.myapplication.masterDetailNavComp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityNavCompBinding

class NavCompActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavCompBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavCompBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, NavCompActivity::class.java))
        }
    }
}