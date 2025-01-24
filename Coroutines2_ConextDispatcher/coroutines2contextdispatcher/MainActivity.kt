package com.example.coroutines2contextdispatcher

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coroutines2contextdispatcher.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO){
            Log.d(tag , "Starting coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main){
                Log.d(tag , "Setting text  in thread ${Thread.currentThread().name}")
//                val tv = findViewById<TextView>(R.id.Textvieww)
                binding.Textvieww.text = answer
            }
        }
    }
    suspend fun doNetworkCall() : String {
        delay(3000L)
        return "This is the answer of the network call"
    }
}

//CONTEXT LETS US DECIDE IN WHICH THE CURRENT COROUTINE IN WHICH THE THREAD WILL BE EXECUTED
//buildFeatures{
//    viewBinding = true
//}
//lateinit var binding = ActivityMainBinding