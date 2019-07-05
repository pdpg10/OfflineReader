package com.example.offlinereader.ui.list_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinereader.R
import com.example.offlinereader.common.OnItemClickListener
import com.example.offlinereader.model.Link
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_link_view.*

class LinkAdapter(
    context: Context,
    private val linkList: MutableList<Link>
) : RecyclerView.Adapter<LinkVH>() {
    private lateinit var listener: OnItemClickListener<Link>

    init {
        listener = context as OnItemClickListener<Link>
    }

    private val inflater by lazy { LayoutInflater.from(context) }//todo fast lazy thread safe mode LazyThreadSafetyMode.NONE

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LinkVH {

        val view = inflater.inflate(R.layout.item_link_view, parent, false)//todo inflate extension
        //todo item view change root to cardview
        return LinkVH(view, listener)
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

    fun notifyItemChanged(link: Link) {
        val pos = linkList.indexOf(link)
        notifyItemChanged(pos)
    }

}

class LinkVH(
    override val containerView: View,
    listener: OnItemClickListener<Link>
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    private var linkItem: Link? = null
    private val colorActive: Int
    private val colorPassive: Int

    init {
        containerView.setOnClickListener { listener.onItemClick(linkItem!!) }
        colorActive = ContextCompat.getColor(containerView.context, R.color.colorActive)
        colorPassive = ContextCompat.getColor(containerView.context, R.color.colorPassive)
    }

    fun onBind(link: Link) {
        this.linkItem = link
        tv.text = link.url

        val color = if (link.isDownloaded) colorActive
        else colorPassive

        tv.setTextColor(color)
    }
}