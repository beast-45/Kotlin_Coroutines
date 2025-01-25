package com.example.coroutine4jobcancellation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        val job =  GlobalScope.launch(Dispatchers.Default) {
            Log.d(tag , "Starting long running calculation...")
            for(i in 30..40){
                Log.d(tag , "Result for i = $i : ${fib(i)}")
            }
            Log.d(tag , "Ending long running calculation...")c
        }

    }
    fun fib(n :Int) : Long {
        if(n == 0) return 0
        if(n == 1) return 1
        else return fib(n-1) + fib(n-2)
    }
}