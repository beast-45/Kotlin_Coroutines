package com.example.coroutines5asyncawait

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                val answer1 = networkCall1()
//                val answer2 = networkCall2()
//                Log.d(TAG, "onCreate: $answer1")
//                Log.d(TAG, "onCreate: $answer2")
//            }
//            Log.d(TAG, "Time taken is : $time ms")
//        }

        //2025-01-24 14:40:45.293  8846-8879  MainActivity            com.example.coroutines5asyncawait    D  onCreate: This is the resuult of network call 1
        //2025-01-24 14:40:45.293  8846-8879  MainActivity            com.example.coroutines5asyncawait    D  onCreate: This is the resuult of network call 1
        //2025-01-24 14:40:45.293  8846-8879  MainActivity            com.example.coroutines5asyncawait    D  Time taken is : 6009 ms
        // --------------------------------------------------------------------------------------------------------------------------------------
        //This took 3+3 seconds , that's not what we want

//       GlobalScope.launch (Dispatchers.IO){
//           val time = measureTimeMillis {
//               var answer1  : String? = null
//               var answer2  : String? = null
//               val job1 =launch{answer1 = networkCall1()}
//               val job2 =launch{answer2 = networkCall2()}
//               job1.join()
//               job2.join()
//               Log.d(TAG, "Answer1 : $answer1")
//               Log.d(TAG, "Answer2 : $answer2")
//           }
//           Log.d(TAG, "Time taken is : $time ms")
//       }
//        2025-01-24 14:54:40.327  9280-9322  MainActivity            com.example.coroutines5asyncawait    D  Answer1 : This is the resuult of network call 1
//        2025-01-24 14:54:40.327  9280-9322  MainActivity            com.example.coroutines5asyncawait    D  Answer2 : This is the resuult of network call 1
//        2025-01-24 14:54:40.327  9280-9322  MainActivity            com.example.coroutines5asyncawait    D  Time taken is : 3035 ms
        // -----------------------------------------------------------------------------------------
        //This will take 3 seconds but this is actually a very terrible approach

        // We will use Async & Await

        GlobalScope.launch(Dispatchers.IO){
            val time = measureTimeMillis {
                var answer1 = async {networkCall1()}
                var answer2 = async {networkCall2()}
                Log.d(TAG, "Answer1 : ${answer1.await()}")
                Log.d(TAG, "Answer2 : ${answer2.await()}")

            }
            Log.d(TAG , "The time taken is : $time ms")
        }
    }
    suspend fun networkCall1() : String{
        delay(3000L)
        return "This is the resuult of network call 1"
    }
    suspend fun networkCall2() : String{
        delay(3000L)
        return "This is the resuult of network call 1"
    }
}