package com.example.myapplication.masterDetailFragments

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMasterDetailBinding
import com.example.myapplication.masterDetailFragments.fragments.DetailFragment
import com.example.myapplication.masterDetailFragments.fragments.ListFragment
import kotlin.math.log

class MasterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMasterDetailBinding
    private lateinit var vm: VMMasterDetail
    private var isPortrait = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasterDetailBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[VMMasterDetail::class.java]
        setContentView(binding.root)

        isPortrait = resources.getBoolean(R.bool.is_portrait)
        Log.e(TAG, "onCreate: $isPortrait")

        supportActionBar?.title = "Lista"

        setUpObservers()

        Log.e(
            TAG,
            "detailFragLandscape: ${supportFragmentManager.findFragmentById(R.id.fragmentDetail)}",
        )

        Log.e(TAG, "count ${supportFragmentManager.backStackEntryCount}")

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

        Log.e(TAG, "currentFragment $fragment")
//        if (fragment == null) {
//            supportFragmentManager.beginTransaction().apply {
//                add(R.id.fragment_container_view, listFragment)
//                setReorderingAllowed(true)
//                commit()
//            }
//        }

    }

    private fun setUpObservers() {
        vm.itemSelected.observe(this) {
            Log.e(TAG, "setUpObservers: $it")

            if (it.isNotBlank()) {
                supportFragmentManager.beginTransaction().apply {
                    if (isPortrait) {
                        resetBackStack()
                        add(R.id.fragment_container_view, DetailFragment())
                        addToBackStack(null)
                    } else {
                        replace(R.id.fragment_container_view, ListFragment())
                        replace(R.id.fragmentDetail, DetailFragment())
                    }
                    setReorderingAllowed(true)
                    commit()
                }
            }
        }
    }

    private fun resetBackStack() {
        while (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        vm.clearItemSelected()
        super.onBackPressed()
    }

    companion object {
        private val TAG = MasterDetailActivity::class.java.simpleName

        fun newIntent(context: Context) {
            context.startActivity(Intent(context, MasterDetailActivity::class.java))
        }
    }
}