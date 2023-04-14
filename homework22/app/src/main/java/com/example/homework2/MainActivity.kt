package com.example.homework2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioDog = findViewById<RadioButton>(R.id.radioDog)
        val radioCat = findViewById<RadioButton>(R.id.radioCat)

        val textView = findViewById<TextView>(R.id.textView)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val pet = if (radioDog.isChecked) "${radioDog.text}" else "${radioCat.text}"
            textView.text = pet
        }
    }
}