package com.example.myapplication


import android.app.Activity
import android.content.Intent
import com.google.zxing.integration.android.IntentIntegrator

class BarcodeScannerHelper(private val activity: Activity) {

    fun initiateScan() {
        // Инициализация сканера с помощью IntentIntegrator
        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Сканирование...")
        integrator.setCameraId(0)  // Камера для сканирования
        integrator.setBeepEnabled(false)  // Отключаем звук
        integrator.setBarcodeImageEnabled(true)
        integrator.setOrientationLocked(true)  // Заблокировать ориентацию экрана
        integrator.setCaptureActivity(VerticalCaptureActivity::class.java)  // Устанавливаем вертикальную ориентацию для сканера
        integrator.initiateScan()
    }

    // Метод для обработки результата сканирования
    fun handleScanResult(requestCode: Int, resultCode: Int, data: Intent?, onResult: (String?) -> Unit) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            onResult(result.contents)  // Возвращаем содержимое результата через коллбэк
        } else {
            onResult(null)  // Возвращаем null если результат пустой
        }
    }
}
