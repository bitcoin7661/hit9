package com.example.hit7

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class BluetoothManager(private val context: Context, private val dataReceiver: DataReceiver) {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothSocket: BluetoothSocket
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    private val deviceAddress = "2c:cf:67:97:c8:4b" // Bluetooth MAC 주소 입력

    companion object {
        private const val MY_UUID = "00001101-0000-1000-8000-00805F9B34FB" // SPP UUID
    }

    init {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    }

    fun connectToDevice() {
        val device: BluetoothDevice = bluetoothAdapter.getRemoteDevice(deviceAddress)
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Bluetooth 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                return
            }

            bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID))
            bluetoothSocket.connect()

            inputStream = bluetoothSocket.inputStream
            outputStream = bluetoothSocket.outputStream

            // 데이터 수신 시작
            receiveData()

            // 성공적으로 연결되었음을 알림
            Toast.makeText(context, "Bluetooth 연결 성공!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Bluetooth 연결 실패: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    private fun receiveData() {
        Thread {
            while (true) {
                try {
                    val buffer = ByteArray(1024)
                    val bytes = inputStream.read(buffer)

                    val receivedData = String(buffer, 0, bytes).trim()
                    val dataParts = receivedData.split(",") // 6개 데이터 파싱 예: torque, reps, speed, ...

                    if (dataParts.size >= 6) {
                        dataReceiver.onDataReceived(dataParts) // 변경된 부분
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    break
                }
            }
        }.start()
    }


    fun closeConnection() {
        try {
            inputStream.close()
            outputStream.close()
            bluetoothSocket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface DataReceiver {
        fun onDataReceived(data: List<String>)
    }
}
