package com.example.hit9

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EquipmentDetailActivity : AppCompatActivity() {

    private lateinit var tvTorque: TextView
    private lateinit var tvReps: TextView
    private lateinit var tvSpeed: TextView
    private lateinit var tvTitle: TextView
    private lateinit var etTargetTorque: EditText
    private lateinit var etTargetReps: EditText
    private lateinit var bluetoothManager: BluetoothManager // BluetoothManager 인스턴스

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipment_detail)

        // UI 요소 초기화
        tvTorque = findViewById(R.id.tvTorque)
        tvReps = findViewById(R.id.tvReps)
        tvSpeed = findViewById(R.id.tvSpeed)
        tvTitle = findViewById(R.id.tvTitle)
        etTargetTorque = findViewById(R.id.etTargetTorque)
        etTargetReps = findViewById(R.id.etTargetReps)

        // BluetoothManager 초기화
        bluetoothManager = BluetoothManager(this, object : BluetoothManager.DataReceiver {
            override fun onDataReceived(data: List<String>) {
                // 데이터 수신 처리 (필요 시)
            }
        })

        // Intent로부터 운동 기구 이름 가져오기
        val equipmentName = intent.getStringExtra("EQUIPMENT_NAME")
        tvTitle.text = equipmentName // 타이틀 업데이트

        // 저장 기능 버튼 클릭 리스너
        findViewById<Button>(R.id.btnSave).setOnClickListener {
            saveSettings()
        }
    }

    private fun saveSettings() {
        // 사용자 설정 저장 로직 구현
        val targetTorque = etTargetTorque.text.toString().toDoubleOrNull()
        val targetReps = etTargetReps.text.toString().toIntOrNull()

        if (targetTorque != null && targetReps != null) {
            // 사용자 피드백
            Toast.makeText(this, "설정이 저장되었습니다.\n목표 가동범위: $targetTorque Ncm\n목표 횟수: $targetReps 개", Toast.LENGTH_SHORT).show()

            // 블루투스 데이터 가져오기 (여기서는 예시 데이터 사용)
            val dataParts = listOf("10.0", "5", "2.0") // 예시: 토크, 횟수, 속도

            // DataCalculator 인스턴스 생성
            val calculator = DataCalculator()

            // 목표 값을 포함하여 계산
            val results = calculator.calculate(dataParts)

            // 결과를 Bluetooth로 전송
            bluetoothManager.sendData(results) // sendData 메소드 호출
        } else {
            Toast.makeText(this, "모든 필드를 올바르게 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    // UI 업데이트 메소드
    fun updateUI(results: List<String>) {
        runOnUiThread {
            if (results.isNotEmpty()) {
                tvTorque.text = results[0] // 토크 업데이트
                tvReps.text = results[1]   // 횟수 업데이트
                tvSpeed.text = results[2]   // 속도 업데이트
            }
        }
    }
}
