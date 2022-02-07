package com.example.wordgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.util.*

class GameActivity : AppCompatActivity() {
    var timer: TextView? = null
    var guess: EditText? = null
    var guessesLeft: TextView? = null
    var score: TextView? = null
    var checkButton: Button? = null
    var answerButton: Button? = null
    var nextButton: Button? = null
    var linearLayout: LinearLayout? = null
    var start = false
    var howLong = 60000 //I put it to 1 min for now, but should we change the time depending on the difficulty?
    var correct = 0
    var totalProblems = 0
    var flag = true
    var diff = 0
    var letters = 0
    var guessesNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val bundle: Bundle? = intent.extras

        diff = intent.getIntExtra("Diff", -1)
        letters = intent.getIntExtra("Letters", 1)

        timer = findViewById(R.id.timer)
        guess = findViewById(R.id.guess)
        guessesLeft = findViewById(R.id.guessesLeft)
        score = findViewById(R.id.score)
        checkButton = findViewById(R.id.checkButton)
        answerButton = findViewById(R.id.answerButton)
        nextButton = findViewById(R.id.nextButton)
        linearLayout = findViewById(R.id.linearLayout)

        numOfGuesses(diff)
        gameTimer()
    }//onCreate

    fun numOfGuesses(difficulty : Int) {
        if (difficulty == 0) {
            guessesNum = 5
            guessesLeft!!.text = "Guesses Left: " + guessesNum
        }
        else if (difficulty == 1) {
            guessesNum = 4
            guessesLeft!!.text = "Guesses Left: " + guessesNum
        }
        else if (difficulty == 2) {
            guessesNum = 3
            guessesLeft!!.text = "Guesses Left: " + guessesNum
        }
    }

    fun updateTimer(secondsLeft : Int) {
        val minutes = secondsLeft / 60
        val seconds = secondsLeft - (minutes * 60)
        var secondStr = seconds.toString()
        if (seconds <= 9) {
            secondStr = "0$secondStr"
        }
        timer!!.text =  minutes.toString() + ":" + secondStr
    }//updateTimer

    fun gameTimer() {
        start = true
        timer!!.text = "1:00"

        object : CountDownTimer( howLong.toLong(), 1000) {
            override fun onTick(p0: Long) {
                updateTimer((p0 / 1000).toInt())
                howLong -= 1000
            }//onTick

            override fun onFinish() {
                timer!!.text = "0:00"
                Toast.makeText(
                    getApplicationContext(), "Your got " + correct + " out of " +
                            totalProblems + " Correct", Toast.LENGTH_LONG
                ).show()
                start = false;
            }//onFinish
        }.start() //CountDown
    }//gameTimer

    fun check (view : View) {
        //TO DO
    }

    fun hide (view : View) {

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (flag) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
        else {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
        }
        flag = !flag
    }//hide


}//GameActivity