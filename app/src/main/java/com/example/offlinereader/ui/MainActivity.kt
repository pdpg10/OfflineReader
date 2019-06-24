package com.example.offlinereader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.offlinereader.R
import com.example.offlinereader.ui.global.BaseFragment
import com.example.offlinereader.ui.list_fragment.ListFragment

class MainActivity : AppCompatActivity() {

    private val container = R.id.root_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addFragment(ListFragment())
        }
    }

    private fun addFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .add(container, fragment)
            .commit()
    }
}
