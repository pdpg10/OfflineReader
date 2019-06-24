package com.example.offlinereader.ui.list_fragment

import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.offlinereader.R
import com.example.offlinereader.ui.global.BaseFragment
import com.example.offlinereader.ui.list_fragment.adapter.LinkAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment(), TextView.OnEditorActionListener {

    override val layoutRes: Int = R.layout.fragment_list
    private var adapter: LinkAdapter? = null


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
        btn_check.setOnClickListener { validateAndAdd() }
        et_url.setOnEditorActionListener(this)
    }

    override fun onEditorAction(
        v: TextView?,
        actionId: Int,
        event: KeyEvent?
    ): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            validateAndAdd()
            return true
        }
        return false
    }

    private fun validateAndAdd() {
        val url = et_url.text.toString()//todo howework toString():String
        val urlRegex = Patterns.WEB_URL.toRegex()//todo change it
        val isValidate = urlRegex.matches(url)
        if (isValidate) {
            et_url.error = null
            val link = Link(url)
            adapter?.addLink(link)
        } else {
            et_url.error = "wrong url"
        }
    }

    private fun setUpRv() {
        val linkList = loadLinks()
        adapter = LinkAdapter(context!!, linkList)
        rv.adapter = adapter
    }

    private fun loadLinks() = mutableListOf<Link>()

}