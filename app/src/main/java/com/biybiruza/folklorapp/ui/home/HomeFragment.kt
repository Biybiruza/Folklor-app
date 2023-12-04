package com.biybiruza.folklorapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.biybiruza.folklorapp.R
import com.biybiruza.folklorapp.databinding.FragmentHomeBinding
import com.example.folklor.ui.activitys.MarginItemDecoration

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val adapter = PDFListAdapter()
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.recyclerView.addItemDecoration(MarginItemDecoration(6,16))
        binding.recyclerView.adapter = adapter
        adapter.setOnClickItemListener {
            val intent = Intent(requireContext(), PDFReaderViewActivity::class.java)
            intent.putExtra(PDFReaderViewActivity.ID,it)
            startActivity(intent)
        }
    }

}