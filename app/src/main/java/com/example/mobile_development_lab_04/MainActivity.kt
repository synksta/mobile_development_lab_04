package com.example.mobile_development_lab_04

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var buttonNext: Button
    private lateinit var buttonTryAgain: Button
    private lateinit var labelMain: TextView
    private lateinit var labelQuestionCount: TextView

    private val usedQuestionIds = mutableSetOf<Int>()
    private lateinit var currentQuestion: Question
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initViews()
        setupListeners()
        reset()
    }

    private fun initViews() {
        buttonTrue = findViewById(R.id.button_true)
        buttonFalse = findViewById(R.id.button_false)
        buttonNext = findViewById(R.id.button_next)
        buttonTryAgain = findViewById(R.id.button_try_again)
        labelMain = findViewById(R.id.label_main)
        labelQuestionCount = findViewById(R.id.label_question_count)
    }

    private fun setupListeners() {
        buttonTrue.setOnClickListener { checkAnswer(true) }
        buttonFalse.setOnClickListener { checkAnswer(false) }
        buttonNext.setOnClickListener { nextQuestion() }
        buttonTryAgain.setOnClickListener { reset() }
    }

    private fun reset() {
        usedQuestionIds.clear()
        correctAnswers = 0
        currentQuestion = Question.create(generateQuestionId())
        updateLabels()
        buttonTrue.isEnabled = true
        buttonFalse.isEnabled = true
        buttonNext.isEnabled = false
    }

    private fun checkAnswer(value: Boolean) {
        if (currentQuestion.result == value) correctAnswers++
        buttonNext.isEnabled = true
        buttonTrue.isEnabled = false
        buttonFalse.isEnabled = false
    }

    @SuppressLint("SetTextI18n")
    private fun nextQuestion() {
        if (usedQuestionIds.count() < Question.questionsAmount()) {
            currentQuestion = Question.create(generateQuestionId())
            updateLabels()
            buttonTrue.isEnabled = true
            buttonFalse.isEnabled = true
        } else {
            labelMain.text = "Correct answers: $correctAnswers/${Question.questionsAmount()}"
        }
        buttonNext.isEnabled = false
    }

    @SuppressLint("SetTextI18n")
    private fun updateLabels() {
        labelMain.text = currentQuestion.value
        labelQuestionCount.text = "${usedQuestionIds.count()}/${Question.questionsAmount()}"
    }

    private fun generateQuestionId(): Int {
        var id = (0..Question.questionsAmount()).random()
        while (id in usedQuestionIds) {
            id = (0..Question.questionsAmount()).random()
        }
        usedQuestionIds.add(id)
        return id
    }

}
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var buttonTrue: Button
//    private lateinit var buttonFalse: Button
//    private lateinit var buttonNext: Button
//    private lateinit var buttonTryAgain: Button
//    private lateinit var labelMain: TextView
//    private lateinit var labelQuestionCount: TextView
//
//    private val usedQuestionIds = mutableSetOf<Int>()
//
//    private lateinit var currentQuestion : Question
//
//    private var correctAnswers : Int = 0
//
//    @SuppressLint("SetTextI18n")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//
//        buttonTrue = findViewById(R.id.button_true)
//        buttonFalse = findViewById(R.id.button_false)
//        buttonNext = findViewById(R.id.button_next)
//        buttonTryAgain = findViewById(R.id.button_try_again)
//        labelMain = findViewById(R.id.label_main)
//        labelQuestionCount = findViewById(R.id.label_question_count)
//
//        reset()
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//        buttonTrue.setOnClickListener{
//            checkAnswer(true)
//        }
//
//        buttonFalse.setOnClickListener{
//            checkAnswer(false)
//        }
//
//        buttonNext.setOnClickListener{
//            if (usedQuestionIds.count() < Question.questionsAmount()){
//                currentQuestion = Question.create(generateQuestionId())
//                labelMain.text = currentQuestion.value
//                buttonTrue.isEnabled = true
//                buttonFalse.isEnabled = true
//            }
//            else {
//                labelMain.text = "Correct answers: $correctAnswers/${Question.questionsAmount()}"
//            }
//            labelQuestionCount.text = "${usedQuestionIds.count()}/${Question.questionsAmount()}"
//            buttonNext.isEnabled = false
//        }
//
//        buttonTryAgain.setOnClickListener{
//            reset()
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun reset(){
//        usedQuestionIds.clear()
//        correctAnswers = 0
//        currentQuestion = Question.create(generateQuestionId())
//        labelMain.text = currentQuestion.value
//        labelQuestionCount.text = "${usedQuestionIds.count()}/${Question.questionsAmount()}"
//        buttonNext.isEnabled = false
//        buttonTrue.isEnabled = true
//        buttonFalse.isEnabled = true
//    }
//
//    private fun checkAnswer(value : Boolean) {
//        if (currentQuestion.result == value) correctAnswers++
//        buttonNext.isEnabled = true
//        buttonTrue.isEnabled = false
//        buttonFalse.isEnabled = false
//    }
//
//    private fun generateQuestionId(): Int {
//        val id = (0..Question.questionsAmount()).random()
//        if (id in usedQuestionIds) return generateQuestionId()
//        usedQuestionIds.add(id)
//        return id
//    }
//}