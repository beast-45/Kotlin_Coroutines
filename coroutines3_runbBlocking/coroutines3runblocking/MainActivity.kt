package com.example.coroutines3runblocking

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coroutines3runblocking.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

        Log.d(tag, "Before RunBlocking")
        runBlocking {
            launch(Dispatchers.IO){
                delay(3000L)
                Log.d(tag, "Finished IO Coroutine 1")
            }
            launch(Dispatchers.IO){
                delay(3000L)
                Log.d(tag, "Finished IO Coroutine 2")
            }
            Log.d(tag, "Start of RunBlocking")
            delay(5000L)
            Log.d(tag , "End of RunBlocking")

            //Even we have 2 delays in dispatcher . io
            // they don't add up
            //the both lauches will be executed at the same time because they are independent coroutines
        }
        Log.d(tag, "After RunBlocking")

        //Note: The two coroutines run independently and concurrently because they
        // are launched on different threads in the IO dispatcher. The delays do not add up.





//        Log.d(tag, "Before RunBlocking")
//        runBlocking {
//            Log.d(tag, "Start of RunBlocking")
//            delay(5000L)
//            Log.d(tag , "End of RunBlocking")
//        }
//        Log.d(tag, "After RunBlocking")
    }
}

//RunBlocking blocks thr current thread untill the coroutine inside it are completed

//It is mainly used for testing or starting coroutines in
//places where suspending functions cannot be directly used (e.g., in main() or unit tests).