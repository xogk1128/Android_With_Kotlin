package com.example.week7_homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 결과 받아오기, activityResult를 가지고 intent를 시작
        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            // data는 intent 타입
            val msg = it.data?.getIntExtra("resultdata", 0) ?: 0
            // 가져온 값 출력
            findViewById<EditText>(R.id.editText).setText(msg.toString())
        }

        findViewById<Button>(R.id.button)?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val value = findViewById<EditText>(R.id.editText).text.toString().toInt()
            // 인텐트에 내용 담기
            intent.putExtra("userdata", value)
            //startActivity(intent)
            // seconActivity 시작하고 setResult한 결과를 callback으로 받아올 수 있게됨
            activityResult.launch(intent)
        }
    }


}