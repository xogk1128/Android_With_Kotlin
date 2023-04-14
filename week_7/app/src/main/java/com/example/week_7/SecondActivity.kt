package com.example.week_7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    
        // intent가 null인지 판단 아닐경우 키값이 userdata인 value를 가져옴
        val msg = intent?.getStringExtra("userdata") ?: ""
        findViewById<TextView>(R.id.textView).text = "${msg}"

//        // Intent 생성
//        val resultIntent = Intent()
//        // 인텐트에 결과로 보낼 키와 값을 넣음
//        resultIntent.putExtra("resultdata", "result string")
//        // onCreate 할 때 보내줌
//        setResult(RESULT_OK, resultIntent)

        // Back 버튼 눌렀을 때 보내게
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                
                // back 버튼을 누를때 result를 결정해서 setResult함
                val resultIntent = Intent()
                resultIntent.putExtra("resultdata", "result string")
                // 어디서 부르던 상관 x, 액티비티가 종료되기 전 마지막에 불린 결과가 액티비티를 불렀던 쪽으로 되돌아감
                setResult(RESULT_OK, resultIntent)
                // 액티비티 종료
                finish()
            }
        })
        
    }
    override fun onStart() {
        super.onStart()
        println("######################## SecondActivity - onStart ######################")
    }

    override fun onResume() {
        super.onResume()
        println("######################## SecondActivity - onResume ######################")
    }

    override fun onPause() {
        super.onPause()
        println("######################## SecondActivity - onPause ######################")
    }

    override fun onStop() {
        super.onStop()
        println("######################## SecondActivity - onStop ######################")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("######################## SecondActivity - onDestroy ######################")
    }
}