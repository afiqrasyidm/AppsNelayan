package com.example.appsnelayan


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    //val list = ArrayList<IkanModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("myTag","sukses buka main")

        rv_main?.layoutManager = GridLayoutManager(this,1)

        btnSell.setOnClickListener{
            val intent = Intent(this, UploadIkanActivity::class.java)
        // start your next activity
             startActivity(intent)
        }


        ApiRetrofit.create().getIkan().enqueue(object :
            Callback<ArrayList<IkanModel>> {
            override fun onResponse(call: Call<ArrayList<IkanModel>>?, response: Response<ArrayList<IkanModel>>) {
                //Tulis code jika response sukses

                Log.d("myTag","sukses Terima data")

                var listIkan = response?.body() as ArrayList<IkanModel>
                Collections.sort(listIkan)
                val adapter = AdapterIkan(listIkan)
                rv_main.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<ArrayList<IkanModel>?>?, t: Throwable){
                Log.d("myTag","Error saat Terima data")
            }

        })


    }
}
