package com.biybiruza.folklor.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.biybiruza.folklor.R
import com.biybiruza.folklor.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info) {

    private lateinit var binding: FragmentInfoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
    }
}