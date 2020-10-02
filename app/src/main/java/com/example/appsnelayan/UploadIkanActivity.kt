package com.example.appsnelayan

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_upload_ikan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import android.content.Intent


class UploadIkanActivity : AppCompatActivity() {

    var fish_image = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_ikan)



        //BUTTON CLICK
        btnImage.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }


        btnUpload.setOnClickListener {

            var username = edtName.text.toString()
            var fish_price = Integer.parseInt(edtFishPrice.text.toString())
            var fish_name = edtFishName.text.toString()
            var WA_number = edtWaNumber.text.toString()


            ApiRetrofit.create().insertIkan(username, fish_image, WA_number, fish_price, fish_name)
                .enqueue(object : Callback<IkanModel> {
                    override fun onResponse(call: Call<IkanModel>, response: Response<IkanModel>) {
                        Log.d("myTag", "sukses Terima data insert")




                    }

                    override fun onFailure(call: Call<IkanModel>, t: Throwable) {
                        Log.d("myTag", "gagal Terima data insert")
                    }

                })


            val intent = Intent(this, MainActivity::class.java)

            // start your next activity
            startActivity(intent)

        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageView.setImageURI(data?.data)
        }
        val byteArrayOutputStream = ByteArrayOutputStream()
        val bitmap = (imageView.getDrawable() as BitmapDrawable).bitmap
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes: ByteArray = byteArrayOutputStream.toByteArray()
        fish_image = Base64.encodeToString(imageBytes, Base64.DEFAULT)


    }

}




