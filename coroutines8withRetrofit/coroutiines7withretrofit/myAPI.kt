package com.example.coroutiines7withretrofit

import retrofit2.Call
import retrofit2.http.GET

interface myAPI {
    @GET("comments")
    fun getComments() : Call<List<CommentsItem>>
}



// Another way :

//interface myAPI{
//    @GET("comments")
//    suspend fun getComments() : List<CommentsItem>
//
//}