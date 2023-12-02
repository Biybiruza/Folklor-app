package com.biybiruza.folklorapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.biybiruza.folklorapp.R
import com.biybiruza.folklorapp.databinding.ActivityPDFReaderViewBinding
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import java.io.File

class PDFReaderViewActivity : AppCompatActivity() {

    companion object{
        const val ID = "id"
    }

    private lateinit var pdfList: List<String>
    var n = 0
    private lateinit var pdfListTitle: List<String>
    lateinit var binding: ActivityPDFReaderViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPDFReaderViewBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_p_d_f_reader_view)
        pdfList = listOf("Qaraqalpaq folklori. Alpamis.pdf",
                "Qaraqalpaq folklori. Er Ziywar. Qurbanbek.pdf",
                "Qaraqalpaq folklori. Naqil-maqallar.pdf", "Qaraqalpaq folklori. Jumbaqlar.pdf", "Qaraqalpaq folklori. Qoblan.pdf",
                "Qaraqalpaq folklori. Qiriq qiz.pdf", "Qaraqalpaq folklori. Shariyar. Qanshayim (1997).pdf")

        pdfListTitle = listOf("Алпамыс","Ер Зийўар. Қурбанбек","Нақил-мақаллар", "Жумбақлар", "Қоблан",
                "Қириқ қиз", "Шарияр. Қаншайим (1997)")

        //share for
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()

        n = intent.getIntExtra(ID, 0)

        //back button
        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = pdfListTitle[n]

        /*binding.ivBack.setOnClickListener {
            this.onBackPressed()
        }
        binding.tvActionTitle.text = pdfListTitle[n]*/

        //progressBar
        val progress = findViewById<ProgressBar>(R.id.progressBar)

        //pdf reader
        val pdfView = findViewById<PDFView>(R.id.pdfView)
        pdfView.fromAsset(pdfList[n])
            .enableSwipe(true)
            .swipeHorizontal(false)
            .defaultPage(0)
            .enableAnnotationRendering(true)
            .scrollHandle(DefaultScrollHandle(this))
            .spacing(2)
            .load()
        progress.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //back button clicked
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var path = getExternalFilesDir(null)?.absolutePath.toString() + pdfList[n]
        val file = File(path)

        when(item.itemId){
            android.R.id.home -> finish()
            R.id.item_share -> {
                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
                    type = "application/pdf"
                }
                Toast.makeText(this, pdfList[n], Toast.LENGTH_LONG).show()
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}