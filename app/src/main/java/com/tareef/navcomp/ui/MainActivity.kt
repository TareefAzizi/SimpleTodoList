package com.tareef.navcomp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tareef.navcomp.R
import com.tareef.navcomp.ui.adapter.TaskAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // use binding.root instead of R.layout.activity_main

        val obj = GreetingsAnother()
        obj.sayHello()
    }

    interface Greetings {
        fun sayHello()
    }

    class GreetingsImpl : Greetings {
        override fun sayHello() {
            Log.d("debugging", "Hello, welcome to kotlin delegation pattern")
        }
    }

    class GreetingsAnother : Greetings by GreetingsImpl()
}