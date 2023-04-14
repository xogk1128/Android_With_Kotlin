package com.example.week7_homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // intent가 null인지 판단 아닐경우 키값이 userdata인 value를 가져옴
        val msg = intent?.getIntExtra("userdata", 0) ?: 0
        findViewById<TextView>(R.id.textView).text = "${msg}"

        val viewModel = ViewModelProvider(this, MyViewModelFactory(msg))[MyViewModel::class.java]
        viewModel.countLiveData.observe(this){
            findViewById<TextView>(R.id.textView).text = "${it}"

        }

        findViewById<Button>(R.id.buttonInc)?.setOnClickListener{
            viewModel.increaseCount()
        }

        findViewById<Button>(R.id.buttonDec)?.setOnClickListener{
            viewModel.decreaseCount()
        }

        // Back 버튼 눌렀을 때 보내게
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

                // back 버튼을 누를때 result를 결정해서 setResult함
                val resultIntent = Intent()
                val value = findViewById<TextView>(R.id.textView).text.toString().toInt()
                resultIntent.putExtra("resultdata", value)
                // 어디서 부르던 상관 x, 액티비티가 종료되기 전 마지막에 불린 결과가 액티비티를 불렀던 쪽으로 되돌아감
                setResult(RESULT_OK, resultIntent)
                // 액티비티 종료
                finish()
            }
        })
    }
}