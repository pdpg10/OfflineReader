package com.example.offlinereader.common

import com.example.offlinereader.model.Link

interface OnItemChangedLister {
    fun onItemChanged(link: Link)
}