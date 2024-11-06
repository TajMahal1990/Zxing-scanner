package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var scannerHelper: BarcodeScannerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scannerHelper = BarcodeScannerHelper(this)  // Инициализация помощника для сканирования

        // Автоматически запускаем сканирование при запуске приложения
        scannerHelper.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Используем helper для обработки результата сканирования
        scannerHelper.handleScanResult(requestCode, resultCode, data) { result ->
            binding.scanResult.text = result ?: "Сканирование отменено"
        }
    }
}
