package com.siscofran.testcermati.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siscofran.testcermati.R
import com.siscofran.testcermati.data.model.Item
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(private val user: ArrayList<Item>) : RecyclerView.Adapter<MainAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder
            = UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))

    override fun getItemCount(): Int = user.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) = holder.bindUser(user[position])

    override fun getItemId(position: Int): Long {
        setHasStableIds(true)
        return user[position].id.toLong()
    }

    fun refreshAdapter(it: ArrayList<Item>) {
        user.addAll(it)
        notifyItemRangeChanged(0, user.size)
    }

    class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindUser(user: Item) {
            Glide.with(itemView.context)
                .load(user.avatar_url)
                .into(itemView.img_user)
            itemView.name_user.text = user.login
        }

    }
}