package com.biybiruza.folklor.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.biybiruza.folklor.R
import com.biybiruza.folklor.databinding.ItemPhotoBinding

class PhotoAdapter(private val photos: List<Int>):RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> () {

    inner class PhotoViewHolder(val imageView: View) : RecyclerView.ViewHolder(imageView){
        val binding = ItemPhotoBinding.bind(imageView)

        fun populateModel(model: Int){
            binding.imageView.setImageResource(model)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.populateModel(photos[position])
    }

    override fun getItemCount() = photos.size
}