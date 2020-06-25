package com.example.appwidget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.attributes.apply {
//            x = -10
//            y = -10
            height = 600
            width = 600
        }
    }
}