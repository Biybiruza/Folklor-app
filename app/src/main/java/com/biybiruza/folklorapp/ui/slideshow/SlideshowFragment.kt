package com.biybiruza.folklorapp.ui.slideshow

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.biybiruza.folklorapp.R
import com.biybiruza.folklorapp.data.Users
import com.biybiruza.folklorapp.databinding.FragmentSlideshowBinding
import com.example.folklor.ui.activitys.MarginItemDecoration
import com.google.firebase.database.*

class SlideshowFragment : Fragment(R.layout.fragment_slideshow){

    private lateinit var binding: FragmentSlideshowBinding
    private lateinit var mDatabase: DatabaseReference
    private val madapter = UserListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSlideshowBinding.bind(view)

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")
        binding.recyclerViewFirebase.setHasFixedSize(true);
        binding.recyclerViewFirebase.layoutManager = LinearLayoutManager(requireContext());
        binding.recyclerViewFirebase.addItemDecoration(MarginItemDecoration(6,16))
        userListFunction(mDatabase,madapter)

        madapter.setOnClickItemListener { pdfUrl: String, name: String ->
            val intent = Intent(requireContext(), UserReadActivity::class.java)
            intent.putExtra(UserReadActivity.URLID,pdfUrl)
            intent.putExtra(UserReadActivity.ID,name)
            startActivity(intent)
//            Toast.makeText(requireContext(),"item clicked $pdfUrl", Toast.LENGTH_LONG).show()

        }

    }
    private fun userListFunction(mDatabase: DatabaseReference, madapter: UserListAdapter){
        var userList = arrayListOf<Users>()
        // Read from the database
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children){
                    val value = userSnapshot.getValue(Users::class.java)
                    userList.add(value!!)
                }
                madapter.list = userList
                binding.recyclerViewFirebase.adapter = madapter
                Log.d(ContentValues.TAG, "Value is: ")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

}