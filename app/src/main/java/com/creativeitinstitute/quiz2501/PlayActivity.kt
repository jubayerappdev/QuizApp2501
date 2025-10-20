package com.creativeitinstitute.quiz2501

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.creativeitinstitute.quiz2501.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {

    private val viewModel: PlayViewModel by viewModels()

    lateinit var binding: ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showCurrentQuestion()



        binding.nextBtn.setOnClickListener {

            val selectedIndex =
                binding.radioGroupBtn
                    .indexOfChild(
                        findViewById(
                            binding.radioGroupBtn.checkedRadioButtonId
                        ))
            if (selectedIndex != -1){
                viewModel.checkAnswer(selectedIndex)
                setNextQuestion()
            }else{
                Toast.makeText(this, "Please Select any option!!", Toast.LENGTH_LONG).show()
            }

        }


    }

    private fun showCurrentQuestion() {
        val currentQuestion = viewModel.getCurrentQuestion()

        binding.questionTV.text = currentQuestion.question
        binding.option1.text = currentQuestion.options[0]
        binding.option2.text = currentQuestion.options[1]
        binding.option3.text = currentQuestion.options[2]
        binding.option4.text = currentQuestion.options[3]
        binding.radioGroupBtn.clearCheck()

    }

    private fun setNextQuestion() {

        val nextQuestion = viewModel.getNextQuestion()


        if (nextQuestion != null) {
            showCurrentQuestion()
        } else {

            val resultIntent = Intent(this@PlayActivity, ResultActivity::class.java)
            resultIntent.putExtra("score", viewModel.getScore())
            startActivity(resultIntent)
            finish()
        }


    }

}


