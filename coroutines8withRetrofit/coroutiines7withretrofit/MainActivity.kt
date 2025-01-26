package com.example.coroutiines7withretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASEURL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val api = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(myAPI::class.java)

        val retroData = api.getComments()

//        retroData.enqueue(object : Callback<List<CommentsItem>> {
//            override fun onFailure(call: Call<List<CommentsItem>>, t: Throwable) {
//                Log.e(TAG, "Error occurred: ${t.localizedMessage}")
//            }
//
//            override fun onResponse(
//                call: Call<List<CommentsItem>>,
//                response: Response<List<CommentsItem>>
//            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let { comments ->
//                        for (comment in comments) {
//                            Log.d(TAG, comment.toString())
//                        }
//                    }
//                } else {
//                    Log.e(TAG, "Failed to fetch data: ${response.errorBody()?.string()}")
//                }
//            }
//        })

        GlobalScope.launch(Dispatchers.IO) {
            val response = retroData.awaitResponse()
            for (comment in response.body()!!) {
                Log.d(TAG, comment.toString())
            }
        }

        // working as same as above bui in a much efficient way , because enqueue starts ans another Thread
        //we have coroutines which are way more efficient
    }
}
