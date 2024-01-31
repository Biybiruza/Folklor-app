package com.biybiruza.folklor.ui.slideshow

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biybiruza.folklor.R
import com.biybiruza.folklor.data.Users
import com.biybiruza.folklor.databinding.ItemBookBinding
import com.squareup.picasso.Picasso

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    inner class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemBookBinding.bind(itemView)
        fun populateModel(model: Users) {
            Picasso.get().load(model.imageUrl).resize(700,1150).into(binding.ImageViewBook)
            Log.d(model.imageUrl,"magliwmat")
            itemView.setOnClickListener {
                onClick.invoke(model.pdfFileUrl!!, model.name!!)
            }
        }
    }

    private var onClick:(it:String, name:String)->Unit = { s: String, s1: String -> }

    fun setOnClickItemListener(onClick:(it:String,name:String)->Unit){
        this.onClick = onClick
    }

        var list = arrayListOf<Users>()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
            return UserListViewHolder(view)
        }

        override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
            holder.populateModel(list[position])
        }

        override fun getItemCount(): Int = list.size
}