package com.example.offlinereader.ui.page_fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.offlinereader.R
import com.example.offlinereader.common.OnItemChangedLister
import com.example.offlinereader.extension.app
import com.example.offlinereader.model.Link
import com.example.offlinereader.ui.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_page.*

private const val KEY_LINK = "KEY_LINK"
private const val BASE_URL = "https://"

class PageFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_page
    private var link: Link? = null
    private var listener: OnItemChangedLister? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        link = arguments?.getParcelable(KEY_LINK)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpWebView()//todo rn,flutter,cordova
        //todo home work hide softKeyBoard
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnItemChangedLister) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setUpWebView() {
        web_view.webViewClient = WebViewClient()
        web_view.webChromeClient = MyChromeClient { updateLoadedLink() }
        web_view.settings.setAppCacheEnabled(true)
        web_view.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        //offline site
        web_view.loadUrl("$BASE_URL${link?.url}")
        web_view.settings.javaScriptEnabled = true //todo xss
        val pref: SharedPreferences

    }

    private fun updateLoadedLink() {
        val pref = activity?.app()?.pref
        link?.isDownloaded = true
        pref?.changeLinkToDownloaded(link!!)
        listener?.onItemChanged(link!!)

    }

    companion object {
        fun create(link: Link) =
            PageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LINK, link)
                }
            }
    }

    class MyChromeClient(private val action: () -> Unit) : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) action()
        }
    }
}