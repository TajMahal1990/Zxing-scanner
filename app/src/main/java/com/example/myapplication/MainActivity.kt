package com.example.myapplication



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {

    private lateinit var scanButton: Button
    private lateinit var scanResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanButton = findViewById(R.id.scan_button)
        scanResult = findViewById(R.id.scan_result)

        // Запуск сканирования при нажатии на кнопку
        scanButton.setOnClickListener {
            initiateScan()
        }
    }

    private fun initiateScan() {
        // Инициализация сканера с помощью IntentIntegrator
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Сканирование...")
        integrator.setCameraId(0)  // Камера для сканирования
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(true)
        integrator.setOrientationLocked(true)  // Заблокировать ориентацию экрана
        integrator.setCaptureActivity(VerticalCaptureActivity::class.java)  // Устанавливаем вертикальную ориентацию для сканера
        integrator.initiateScan()
    }

    // Обработка результата сканирования
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Получаем результат сканирования
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                scanResult.text = "Сканирование отменено"
            } else {
                scanResult.text = "Результат: ${result.contents}"
            }
        }
    }
}
