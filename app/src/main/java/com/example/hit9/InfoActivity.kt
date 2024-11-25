package com.example.hit9

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back)

        // Intent로 전달된 데이터 가져오기
        val part = intent.getStringExtra("PART")

        // TextView에 운동 부위 정보 표시
        val textView = findViewById<TextView>(R.id.tvTitle)
        textView.text = part ?: "운동 부위 정보 없음"
    }
}