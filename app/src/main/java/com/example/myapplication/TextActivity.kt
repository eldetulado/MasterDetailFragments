package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityTextBinding

class TextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("hola", Context.MODE_PRIVATE)

        val email = prefs.getString("email", "")
        val pass = prefs.getString("pass", "")

        binding.tvData.text = "$email - $pass"

        binding.btnClose.setOnClickListener {
            prefs.edit().apply {
                clear()
                apply()
            }
            SplashActivity.newIntent(this)
            finish()
        }
    }

    companion object {
        private val TAG = TextActivity::class.java.simpleName

        fun newIntent(context: Context) {
            context.startActivity(Intent(context, TextActivity::class.java))
        }
    }
}