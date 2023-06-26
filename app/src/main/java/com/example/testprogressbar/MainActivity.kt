package com.example.testprogressbar

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnNext: Button

    private lateinit var timerText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var countdownTimer: CountDownTimer
    private val totalTime = 30000 // Загальний час у мілісекундах
    private val interval = 100 // Інтервал оновлення прогресу у мілісекундах

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNext = findViewById(R.id.btn_next_activity)
        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        timerText = findViewById(R.id.timer_text)
        progressBar = findViewById(R.id.progressBar)
        progressBar.max = 100 // Встановлення максимального значення прогресу
        progressBar.scaleX = -1f

           startCountdownTimer()
    }


    private fun startCountdownTimer() {
        val progressIncrement =
            100f / (totalTime / interval) // Приріст прогресу на кожному інтервалі
        var currentProgress = 0f // Поточне значення прогресу

        countdownTimer = object : CountDownTimer(totalTime.toLong(), interval.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                currentProgress += progressIncrement
                progressBar.progress = currentProgress.toInt()
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                timerText.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                progressBar.progress = 100 // Завершення прогресу на 100%
                timerText.text = "0"
            }
        }
        countdownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countdownTimer.cancel()
    }
}