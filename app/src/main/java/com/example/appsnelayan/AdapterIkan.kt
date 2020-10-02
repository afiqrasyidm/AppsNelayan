package com.example.appsnelayan

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_ikan.view.*
import kotlinx.android.synthetic.main.list_ikan.view.imageView
import java.util.*
import kotlin.collections.ArrayList

class AdapterIkan(val listIkan: ArrayList<IkanModel>)
    : RecyclerView.Adapter<AdapterIkan.MyHolder>()
{
    inner class MyHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterIkan.MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_ikan, parent, false)
        return MyHolder(view)

    }

    override fun getItemCount(): Int {
        return listIkan.size
    }

    override fun onBindViewHolder(holder: AdapterIkan.MyHolder, position: Int) {

        holder.itemView.tvFishName.text =  listIkan.get(position).fishName
        holder.itemView.tvFishPrice.text =  "RP. "+listIkan.get(position).fishPrice.toString()
        holder.itemView.imageView.setImageBitmap(change_base64_to_bitmap( listIkan.get(position).fishImage));

        holder.itemView.btnBuy.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailAcitivity::class.java)

            intent.putExtra("NAMA",listIkan[position].username)
            intent.putExtra("NAMA_IKAN",listIkan[position].fishName)
            intent.putExtra("HARGA_IKAN",listIkan[position].fishPrice)
            intent.putExtra("IMG",listIkan[position].fishImage)
            intent.putExtra("WA_number",listIkan[position].wANumber )


            holder.itemView.context.startActivity(intent)
        }
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