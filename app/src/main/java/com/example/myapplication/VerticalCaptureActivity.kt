package com.example.myapplication


import com.journeyapps.barcodescanner.CaptureActivity

// Кастомная активность для вертикального сканирования
class VerticalCaptureActivity : CaptureActivity() {
    // Здесь мы можем настроить вертикальное сканирование
    override fun onResume() {
        super.onResume()
        requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  // Устанавливаем вертикальную ориентацию
    }
}
