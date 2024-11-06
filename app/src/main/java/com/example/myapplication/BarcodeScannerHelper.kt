package com.example.myapplication


import android.app.Activity
import android.content.Intent
import com.google.zxing.integration.android.IntentIntegrator

class BarcodeScannerHelper(private val activity: Activity) {

    fun initiateScan() {
        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Сканирование...")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)
        integrator.setOrientationLocked(true)
        integrator.setCaptureActivity(VerticalCaptureActivity::class.java)
        integrator.initiateScan()
    }

    fun handleScanResult(requestCode: Int, resultCode: Int, data: Intent?, onResult: (String?) -> Unit) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        onResult(result?.contents)
    }
}
