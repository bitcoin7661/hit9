package com.example.hit9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EquipmentChest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chest)

        // 버튼 클릭 리스너 설정
        findViewById<Button>(R.id.btnbanchpress).setOnClickListener { openInfo("벤치프레스") }
        findViewById<Button>(R.id.btndumbelpress).setOnClickListener { openInfo("덤벨프레스") }
        findViewById<Button>(R.id.btnPushup).setOnClickListener { openInfo("푸쉬업") }
        findViewById<Button>(R.id.btninclinepress).setOnClickListener { openInfo("인클라인프레스") }
    }


    private fun openInfo(equipment: String) {
        // 선택된 운동 기구에 대한 정보를 보여줄 Activity를 실행
        val intent = Intent(this, EquipmentDetailActivity::class.java)
        intent.putExtra("EQUIPMENT_NAME", equipment) // 선택한 운동 기구 이름을 전달
        startActivity(intent) // Activity 실행
    }
}
