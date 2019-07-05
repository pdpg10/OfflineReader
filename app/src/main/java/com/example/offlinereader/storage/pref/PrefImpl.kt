package com.example.offlinereader.storage.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.offlinereader.model.Link
import com.google.gson.Gson

private const val KEY_LINK_LIST = "KEY_LINK_LIST"

class PrefImpl(
    context: Context
) : IPref {

    private val pref: SharedPreferences
    private val gson = Gson()

    init {
        pref = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun readLinks(): MutableList<Link> {
        val jsonStr = pref.getString(KEY_LINK_LIST, "")
        if (jsonStr.isNullOrEmpty()) return mutableListOf()
        val links: Array<Link> = gson.fromJson(jsonStr, Array<Link>::class.java)
        return mutableListOf(*links)
    }

    override fun addLink(link: Link) {
        val jsonStr = pref.getString(KEY_LINK_LIST, "")

        val list = if (jsonStr.isNullOrEmpty()) {
            mutableListOf()
        } else {
            val links: Array<Link> = gson.fromJson(jsonStr, Array<Link>::class.java)
            mutableListOf(*links)
        }


        list.add(0, link)
        val newArray = list.toTypedArray()
        val newJsonStr = gson.toJson(newArray)

        pref.edit().apply {
            putString(KEY_LINK_LIST, newJsonStr)
            apply()
        }

    }

    override fun changeLinkToDownloaded(link: Link) {
        val list = readLinks()
        for (it in list) {
            if (it.url == link.url) {
                it.isDownloaded = true
                break
            }
        }
        val jsonStr = gson.toJson(list)
        pref.edit().apply {
            putString(KEY_LINK_LIST, jsonStr)
            apply()
        }
    }
}