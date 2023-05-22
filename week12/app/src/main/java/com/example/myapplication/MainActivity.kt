package com.example.myapplication

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }




    }

    override fun onStart() {
        super.onStart()
        val text = findViewById<TextView>(R.id.textView)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val name = pref.getString("name", "")
        val size = pref.getString("size", "")
        when(size){
            "big" -> text.setTextSize(Dimension.SP, 20F)
            "medium" -> text.setTextSize(Dimension.SP, 14F)
            "small" -> text.setTextSize(Dimension.SP, 10F)
            else -> text.setTextSize(Dimension.SP, 14F)
        }
        text.setText(name)
        val font = pref.getBoolean("italic", false)
        when(font){
            false -> text.setTypeface(null, Typeface.NORMAL)
            true -> text.setTypeface(null, Typeface.ITALIC)
            else -> text.setTypeface(null, Typeface.NORMAL)
        }
    }
}