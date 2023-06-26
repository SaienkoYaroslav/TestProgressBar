package com.example.testprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var timerText: TextView
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        progressBar = findViewById(R.id.progress_bar)
        timerText = findViewById(R.id.timer_text)

        val totalTime = 30000 // 30 seconds
        val interval = 1000 // 1 second

        countDownTimer = object : CountDownTimer(totalTime.toLong(), interval.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((totalTime - millisUntilFinished) * 100 / totalTime).toInt()
                progressBar.progress = progress
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                timerText.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                progressBar.progress = 100
                timerText.text = "0"
            }
        }

        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}