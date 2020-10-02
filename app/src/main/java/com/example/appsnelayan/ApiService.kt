package com.example.appsnelayan


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*


interface ApiService {

    @GET("ikan")
    fun getIkan(): Call<ArrayList<IkanModel>>

    @FormUrlEncoded
    @POST("ikan")
    fun insertIkan(
        @Field("username") username: String?,
        @Field("fish_image") fish_image: String?,
        @Field("WA_number") WA_number: String?,
        @Field("fish_price") fish_price: Int?,
        @Field("fish_name") fish_name: String?
    ):  Call<IkanModel>

}
