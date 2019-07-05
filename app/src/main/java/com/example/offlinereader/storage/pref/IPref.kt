package com.example.offlinereader.storage.pref

import com.example.offlinereader.model.Link

interface IPref {
    fun addLink(link: Link)

    fun readLinks(): MutableList<Link>

    fun changeLinkToDownloaded(link: Link)

}