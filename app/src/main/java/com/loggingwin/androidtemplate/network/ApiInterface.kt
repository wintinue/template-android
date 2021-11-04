package com.loggingwin.androidtemplate.network

import okhttp3.MultipartBody
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    /* GET */
    @GET("/your-route")
    fun getSample(@Query("id") id: String): Call<Response>

    /* POST */
    @POST("/your-route")
    fun postSample(@Body params: Any): Call<Response>

    @FormUrlEncoded
    @POST("/your-route")
    fun postSample(@Field("id") id: String): Call<Response>

    @Multipart
    @POST("/your-route")
    fun postImageSample(@Part image: MultipartBody.Part): Call<Response>

    @Multipart
    @POST("/your-route")
    fun postImagesSample(@Part images: ArrayList<MultipartBody.Part>): Call<Response>

    /* PUT */
    @PUT("/post/{postIdx}")
    fun putPost(@Path("postIdx") postIdx: Int, @Body params: Any): Call<Response>

    /* PATCH */
    @PATCH("/your-route/{id}")
    fun patchSample(@Path("id") id: String, @Body params: Any): Call<Response>

    /* DELETE */
    @DELETE("/your-route")
    fun deleteSample(): Call<Response>

}