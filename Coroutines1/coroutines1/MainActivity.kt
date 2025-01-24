package com.example.coroutines1

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        GlobalScope.launch {
            delay(5000L)
            Log.d(tag , "coroutine says hello from thread ${Thread.currentThread().name}")
        }
        Log.d(tag , "hello from thread ${Thread.currentThread().name}")

        //suspend functions : spacial functions that can be paused and resumed later without blocking the main thread
        //they are defined using suspend keyword and are essential in coroutine based programming for handling asynchronous operations

        //key features :
        //NON_BLOCKING
        //Pause and resume
        //can only be called from / within a coroutine or another suspend function

        GlobalScope.launch {
            val net1 = doNetworkCall()
            val net2 = doNetworkCall2()

            Log.d(tag , net1)
            Log.d(tag , net2)
        }
    }

    suspend fun doNetworkCall() : String{
        delay(3000L)
        return "this is the first answer"
    }
    suspend fun doNetworkCall2() : String{
        delay(3000L)
        return "this is the second answer"
    }
}