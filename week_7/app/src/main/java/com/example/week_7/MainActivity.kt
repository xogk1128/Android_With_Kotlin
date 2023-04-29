package com.example.week_7

import android.annotation.SuppressLint
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 결과 받아오기, activityResult를 가지고 intent를 시작
        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            // data는 intent 타입
            val msg = it.data?.getStringExtra("resultdata") ?: ""
            // 가져온 값 snackbar로 출력
            Snackbar.make(findViewById(R.id.button), msg, Snackbar.LENGTH_SHORT).show()
        }
        
        findViewById<Button>(R.id.button)?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            // 인텐트에 내용 담기
            intent.putExtra("userdata", "hello")
            //startActivity(intent)
            // seconActivity 시작하고 setResult한 결과를 callback으로 받아올 수 있게됨
            activityResult.launch(intent)
        }

        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.countLiveData.observe(this){
            findViewById<TextView>(R.id.textView3).text = "${it}"

        }

        findViewById<Button>(R.id.button2)?.setOnClickListener{
            viewModel.increaseCount()
        }

        findViewById<Button>(R.id.button3)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:114"))
            startActivity(intent);
        }
    }

    override fun onStart() {
        super.onStart()
        println("######################## MainActivity - onStart ######################")
    }

    override fun onResume() {
        super.onResume()
        println("######################## MainActivity - onResume ######################")
    }

    override fun onPause() {
        super.onPause()
        println("######################## MainActivity - onPause ######################")
    }

    override fun onStop() {
        super.onStop()
        println("######################## MainActivity - onStop ######################")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("######################## MainActivity - onDestroy ######################")
    }
}