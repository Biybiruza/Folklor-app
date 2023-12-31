package com.biybiruza.folklorapp.ui.slideshow

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.biybiruza.folklorapp.R
import com.biybiruza.folklorapp.databinding.ActivityUserReadBinding
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class UserReadActivity : AppCompatActivity() {

    companion object {
        const val ID = "id"
        const val URLID = "uriId"
    }

    private lateinit var binding: ActivityUserReadBinding

    var s = ""
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        s = intent.getStringExtra(URLID) ?: ""
        name = intent.getStringExtra(ID) ?: ""

        binding.tvTitle.text= name
        //back button
        binding.backBtn.setOnClickListener {
            finish()
        }

        RetrivePdfStream().execute(s)
    }

    inner class RetrivePdfStream : AsyncTask<String?, Void?, InputStream?>() {

        // Here load the pdf and dismiss the dialog box
        override fun onPostExecute(inputStream: InputStream?) {

            binding.pdfViewUser.fromStream(inputStream)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .scrollHandle(DefaultScrollHandle(this@UserReadActivity))
                .spacing(2)
                .load()

            val view = layoutInflater.inflate(R.layout.custom_dialog,null)
            val alertDialog = AlertDialog.Builder(this@UserReadActivity,R.style.CustomAlertDialog)
//                .setTitle("Хабарландириў")
//                .setMessage(R.string.alert_dialog)
                .setView(view).create()
            alertDialog.show()
            Handler().postDelayed({
                alertDialog.dismiss()
            },3000)

            binding.progress.visibility = View.GONE
        }

        override fun doInBackground(vararg p0: String?): InputStream? {
            var inputStream: InputStream? = null
            try {
                // adding url
                val url = URL(p0[0])
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

                // if url connection response code is 200 means ok the execute
                if (urlConnection.responseCode == 200) {
                    inputStream = BufferedInputStream(urlConnection.inputStream)
                }
            } // if error return null
            catch (e: IOException) {
                return null
            }
            return inputStream
        }
    }

    //back button clicked
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

//        var path = getExternalFilesDir(null)?.absolutePath.toString() + pdfList[n]
//        val file = File(path)

        when (item.itemId) {
            android.R.id.home -> finish()
//            R.id.item_share -> {
//                val shareIntent: Intent = Intent().apply {
//                    action = Intent.ACTION_SEND
//                    putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
//                    type = "application/pdf"
//                }
//                Toast.makeText(this, pdfList[n], Toast.LENGTH_LONG).show()
//                startActivity(shareIntent)
//            }
        }
        return super.onOptionsItemSelected(item)
    }

}