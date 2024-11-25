package com.example.hit9

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChestDetailActivity : AppCompatActivity() {

    private lateinit var tvTorque2: TextView
    private lateinit var tvReps2: TextView
    private lateinit var tvSpeed2: TextView
    private lateinit var tvTitle2: TextView
    private lateinit var etTargetTorque2: EditText
    private lateinit var etTargetReps: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chest_detail) // XML 레이아웃 파일 설정

        // UI 요소 초기화
        tvTorque2 = findViewById(R.id.tvTorque2)
        tvReps2 = findViewById(R.id.tvReps2)
        tvSpeed2 = findViewById(R.id.tvSpeed2)
        tvTitle2 = findViewById(R.id.tvTitle2) // 타이틀 텍스트뷰 초기화
        etTargetTorque2 = findViewById(R.id.etTargetTorque2)
        etTargetReps = findViewById(R.id.etTargetReps)

        // Intent로부터 운동 기구 이름 가져오기
        val equipmentName = intent.getStringExtra("EQUIPMENT_NAME")
        tvTitle2.text = equipmentName // 타이틀 업데이트

        // 저장 기능 버튼 클릭 리스너
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveSettings()
        }
    }

    private fun saveSettings() {
        // 사용자 설정 저장 로직 구현
        val targetTorque = etTargetTorque2.text.toString()
        val targetReps = etTargetReps.text.toString()

        // 기본적인 사용자 피드백 제공
        if (targetTorque.isNotEmpty() && targetReps.isNotEmpty()) {
            Toast.makeText(this, "설정이 저장되었습니다.\n목표 가동범위: $targetTorque Ncm\n목표 횟수: $targetReps 개", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}
