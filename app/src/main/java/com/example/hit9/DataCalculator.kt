package com.example.hit9

class DataCalculator {

    /**
     * 블루투스 데이터로부터 토크, 횟수, 속도를 계산합니다.
     * @param data 블루투스에서 수신한 데이터 리스트
     * @return 계산 결과를 포함한 문자열 리스트
     */
    fun calculate(data: List<String>): List<String> {
        if (data.size < 3) {
            throw IllegalArgumentException("데이터 항목이 부족합니다.")
        }

        // 데이터 변환
        val torque = data[0].toDoubleOrNull() ?: 0.0  // 첫 번째 항목: 토크
        val reps = data[1].toIntOrNull() ?: 0          // 두 번째 항목: 횟수
        val speed = data[2].toDoubleOrNull() ?: 0.0    // 세 번째 항목: 속도

        // 평균 속도 계산
        val averageSpeed = if (reps > 0) torque / reps else 0.0  // 평균 속도 계산

        // 총 운동 거리 계산 (예: 속도 * 시간 (가정된 시간))
        val totalDistance = speed * reps  // 예를 들어, 총 거리 = 속도 * 횟수

        // 운동 효율성 계산 (예: 토크와 횟수를 기준으로)
        val efficiency = if (reps > 0) torque / reps else 0.0 // 예시적인 효율성 계산

        // 결과 반환
        return listOf(
            "토크: ${torque} Ncm",
            "횟수: ${reps} 개",
            "평균 속도: ${averageSpeed} m/s",
            "총 운동 거리: ${totalDistance} m",
            "운동 효율성: ${efficiency}"
        )
    }
}
