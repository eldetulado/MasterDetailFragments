package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.myapplication.masterDetailFragments.MasterDetailActivity
import com.example.myapplication.masterDetailNavComp.NavCompActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        Handler().postDelayed({
//            val prefs = getSharedPreferences("hola", Context.MODE_PRIVATE)
//            val isSessionOpen = prefs.getBoolean("session", false)
//            if (isSessionOpen) {
//                TextActivity.newIntent(this)
//            } else {
//                MainActivity.newIntent(this)
//            }
            NavCompActivity.newIntent(this)
            finish()
        }, 1000)

    }

    companion object {
        private val TAG = SplashActivity::class.java.simpleName

        fun newIntent(context: Context) {
            context.startActivity(Intent(context, SplashActivity::class.java))
        }
    }
}