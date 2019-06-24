package com.example.offlinereader.ui.list_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinereader.R
import com.example.offlinereader.ui.list_fragment.Link
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_link_view.*

class LinkAdapter(
    context: Context,
    private val linkList: MutableList<Link>
) : RecyclerView.Adapter<LinkVH>() {

    private val inflater by lazy { LayoutInflater.from(context) }//todo fast lazy thread safe mode LazyThreadSafetyMode.NONE


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LinkVH {

        val view = inflater.inflate(R.layout.item_link_view, parent, false)//todo inflate extension
        //todo item view change root to cardview
        return LinkVH(view)
    }

    override fun getItemCount() = linkList.size

    override fun onBindViewHolder(
        holder: LinkVH,
        position: Int
    ) {
        val link = linkList[position]
        holder.onBind(link)
    }

    fun addLink(newLink: Link) {
        linkList.add(0, newLink)
        notifyItemInserted(0)
    }

}

class LinkVH(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun onBind(link: Link) {
        tv.text = link.url
        tv.isEnabled = link.isDownloaded
    }
}