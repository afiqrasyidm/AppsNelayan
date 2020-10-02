package com.example.appsnelayan

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail_acitivity.*
import kotlinx.android.synthetic.main.list_ikan.view.*


class DetailAcitivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_acitivity)


        var nama = intent.getStringExtra("NAMA")
        var img = intent.getStringExtra("IMG")
        var nama_ikan = intent.getStringExtra("NAMA_IKAN")
        var harga_ikan = intent.getIntExtra("HARGA_IKAN",1)
        var wa_nelayan =  intent.getStringExtra("WA_number")

        tvFishName.text = nama_ikan
        tvFishPrice.text = "RP."+ harga_ikan.toString()
        tvFisherName.text = "Nama Nelayan "+nama
        imageView.setImageBitmap(change_base64_to_bitmap(img))
        btnWA.setOnClickListener(View.OnClickListener {
            val url = "https://wa.me/"+wa_nelayan
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        })

    }
    fun  change_base64_to_bitmap  (  base64string : String? ) : Bitmap? {
        try {

            val decodedString  = Base64.decode(base64string, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }
        catch(e: Exception){
            return null
        }

    }

}
