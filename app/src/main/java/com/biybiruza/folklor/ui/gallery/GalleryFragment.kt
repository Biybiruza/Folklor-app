package com.biybiruza.folklor.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.biybiruza.folklor.R
import com.biybiruza.folklor.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private lateinit var binding: FragmentGalleryBinding

    private var photoList = arrayOf(R.drawable.foto1,R.drawable.foto2,R.drawable.foto3,R.drawable.foto4,R.drawable.foto5)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)

        binding.carouselView.setImageListener { position, imageView ->
            imageView.setImageResource(photoList[position])
        }
        binding.carouselView.pageCount = photoList.count()
    }
}