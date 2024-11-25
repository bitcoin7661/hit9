package com.example.hit9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EquipmentBack : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back)

        // 버튼 클릭 리스너 설정
        findViewById<Button>(R.id.btnLatPulldown).setOnClickListener { openInfo("랫풀다운") }
        findViewById<Button>(R.id.btnSeatedRow).setOnClickListener { openInfo("시티드로우") }
        findViewById<Button>(R.id.btnPullup).setOnClickListener { openInfo("풀업") }
        findViewById<Button>(R.id.btnBarbellRow).setOnClickListener { openInfo("바벨로우") }
    }


    private fun openInfo(equipment: String) {
        val intent = if (equipment == "가슴") {
            Intent(this, EquipmentChest::class.java)
        } else {
            Intent(this, EquipmentDetailActivity::class.java)
        }
        intent.putExtra("EQUIPMENT_NAME", equipment) // 선택한 운동 기구 이름을 전달
        startActivity(intent) // Activity 실행
    }
}