package com.example.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizgame.databinding.ActivityGameOverBinding

class GameOverActivity : AppCompatActivity() {

    private lateinit var bindingGameOver : ActivityGameOverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGameOver = ActivityGameOverBinding.inflate(layoutInflater)
        setContentView(bindingGameOver.root)

        val correctAnswer = intent.getIntExtra("correct", 0)
        val questions = intent.getIntExtra("questions", 0)

        bindingGameOver.tvScore.text = "$correctAnswer / $questions"

        bindingGameOver.btnTryAgain.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

    }
}