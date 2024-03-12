package com.example.harjoitus3_6

interface TimerCallback {
    fun updateTimer(seconds: Int, formattedTime: String)
}

class MyTimer(val callback: TimerCallback) {

    var secondsCount = 0
    private lateinit var runnable: Runnable
    private var handler = android.os.Handler()

    fun startTimer() {
        runnable = Runnable {
            secondsCount++
            val formattedTime = formatTime(secondsCount / 60, secondsCount % 60)
            callback.updateTimer(secondsCount, formattedTime)
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

    private fun formatTime(minutes: Int, seconds: Int): String {
        val formattedSeconds = if (seconds < 10) "0$seconds" else "$seconds"
        return "$minutes : $formattedSeconds"
    }
}