package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.setPadding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    //    private val obs = InternetController(this)
    private lateinit var vm: MyVM
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        vm = ViewModelProvider(this)[MyVM::class.java]

        val prefs = getSharedPreferences("hola", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            prefs.edit().apply {
                putString("email", binding.etEmail.text.toString())
                putString("pass", binding.etPassword.text.toString())
                putBoolean("session", binding.checkbox.isChecked)
                apply()
            }

            TextActivity.newIntent(this)
            finish()
        }

    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        fun newIntent(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}

class MyVM : ViewModel() {
    val progress = MutableLiveData(0)

    fun addProgress(v: Int) {
        progress.value = progress.value!! + v
    }

    fun resetProgress() {
        progress.value = 0
    }
}


//class InternetController(private val context: Context) : LifecycleEventObserver {
//
//    var haveConnection = false;
//
//    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
//        if (event == Lifecycle.Event.ON_CREATE) {
//            start()
//        }
//
//        if (event == Lifecycle.Event.ON_STOP) {
//            stop()
//        }
//    }
//
//    private fun start() {
//        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
////
////            capabilities?.let {
////                val connMobileData = it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
////                val connWifi = it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
////
////                haveConnection = connMobileData || connWifi
////            }
////        } else {
//        haveConnection = cm.activeNetworkInfo?.isConnected ?: false
////        }
//    }
//
//    private fun stop() {
//        haveConnection = false
//    }
//}