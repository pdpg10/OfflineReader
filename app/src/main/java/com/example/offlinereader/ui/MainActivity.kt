package com.example.offlinereader.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.offlinereader.R
import com.example.offlinereader.common.OnItemChangedLister
import com.example.offlinereader.common.OnItemClickListener
import com.example.offlinereader.model.Link
import com.example.offlinereader.ui.global.BaseFragment
import com.example.offlinereader.ui.list_fragment.ListFragment
import com.example.offlinereader.ui.page_fragment.PageFragment

class MainActivity : AppCompatActivity()
    , OnItemClickListener<Link>
    , OnItemChangedLister {

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
            .add(container, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    override fun onItemChanged(link: Link) {
        val fragment = supportFragmentManager.findFragmentByTag(ListFragment::class.java.simpleName) as? ListFragment
        fragment?.notifyByPosition(link)
    }

    override fun onItemClick(it: Link) = addFragment(PageFragment.create(it))
}
