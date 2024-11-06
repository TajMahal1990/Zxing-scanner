package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var scanResult: TextView
    private lateinit var scannerHelper: BarcodeScannerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanResult = findViewById(R.id.scan_result)
        scannerHelper = BarcodeScannerHelper(this)  // Инициализация помощника для сканирования

        // Автоматически запускаем сканирование при запуске приложения
        scannerHelper.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Используем helper для обработки результата сканирования
        scannerHelper.handleScanResult(requestCode, resultCode, data) { result ->
            scanResult.text = result ?: "Сканирование отменено"
        }
    }
}
