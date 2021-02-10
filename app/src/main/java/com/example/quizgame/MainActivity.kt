package com.example.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /* Default value of the question and score */
    var questionNumber = 1
    var correctAnswers = 0

    val images = Lists.images
    val options = Lists.options

    var correctAnswer = ""

    var generatedQuestions: MutableList<Int> = ArrayList()
    var generatedOptions: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generateQuestion()

        binding.buttonSubmit.setOnClickListener {
            questionNumber++

            val id = binding.radioGroup.checkedRadioButtonId

            when (id) {
                R.id.rb_1 -> {
                    if (correctAnswer == binding.rb1.text.toString()) {
                        showToast("Correct Answer")
                        correctAnswers++
                        generateQuestion()
                    } else {
                        showToast("Incorrect Answer")
                        generateQuestion()
                    }
                }
                R.id.rb_2 -> {
                    if (correctAnswer == binding.rb2.text.toString()) {
                        showToast("Correct Answer")
                        correctAnswers++
                        generateQuestion()
                    } else {
                        showToast("Incorrect Answer")
                        generateQuestion()
                    }
                }
                R.id.rb_3 -> {
                    if (correctAnswer == binding.rb3.text.toString()) {
                        showToast("Correct Answer")
                        correctAnswers++
                        generateQuestion()
                    } else {
                        showToast("Incorrect Answer")
                        generateQuestion()
                    }
                }
                R.id.rb_4 -> {
                    if (correctAnswer == binding.rb4.text.toString()) {
                        showToast("Correct Answer")
                        correctAnswers++
                        generateQuestion()
                    } else {
                        showToast("Incorrect Answer")
                        generateQuestion()
                    }
                }
            }

            /* This level has 10 questions */
            if (questionNumber > 10) {
                binding.tvQuestionNumber.text = (questionNumber - 1).toString()
                gameOver()
            }
        }


    }

    private fun generateQuestion() {
        if (questionNumber != 11) {
            // The first radio button is selected as a default
            binding.rb1.isChecked = true
            binding.rb2.isChecked = false
            binding.rb3.isChecked = false
            binding.rb4.isChecked = false

            generatedQuestions.clear()

            binding.tvQuestionNumber.text = questionNumber.toString()
            binding.tvCorrectAnswers.text = correctAnswers.toString()

            /* Images */
            var random = (images.indices).random()
            while (random in generatedQuestions) {
                random = (images.indices).random()
            }

            generatedQuestions.add(random)
            binding.imgView.setImageResource(images[random])

            correctAnswer = options[random]

            generatedOptions.add(random)

            val radioButtons = mutableListOf<Int>(
                    R.id.rb_1,
                    R.id.rb_2,
                    R.id.rb_3,
                    R.id.rb_4
            )

            val randomLocation = (0 until 4).random()
            val correctButton = findViewById<RadioButton>(radioButtons[randomLocation])
            correctButton.text = correctAnswer

            /*Randomly choosen incorrect answers*/
            for (i in 0 until 4) {
                if (i == randomLocation)
                    continue

                val radioButton = findViewById<RadioButton>(radioButtons[i])
                random = (options.indices).random()
                while (random in generatedQuestions) {
                    random = (options.indices).random()
                }
                generatedQuestions.add(random)
                radioButton.text = options[random]
            }

        }
    }

    private fun gameOver() {
        val intent = Intent(applicationContext, GameOverActivity::class.java)
        intent.putExtra("correct", correctAnswers)
        intent.putExtra("questions", questionNumber - 1)
        startActivity(intent)
        finish()
    }

    private fun showToast(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }


}