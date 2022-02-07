package com.example.wordgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.util.*
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.collections.ArrayList


class GameActivity : AppCompatActivity() {
    var timer: TextView? = null
    var guess: EditText? = null
    var guessesLeft: TextView? = null
    var score: TextView? = null
    var checkButton: Button? = null
    var answerButton: Button? = null
    var nextButton: Button? = null
    var linearLayout: LinearLayout? = null
    lateinit var answerLayout : LinearLayout
    var start = false
    var howLong = 60000
    var correct = 0
    var totalProblems = 0
    var flag = true
    var diff = 0
    var time = 60000
    var guessesNum = 0
    var correctAnswer = ""
    var userAnswer = ""
    var correctGuessMade = false

    //for test; substitute later?
    val easyWords = arrayOf("Moon", "Hand", "Seed", "Deal", "Coke")
    val midWords = arrayOf("Pasta", "Event", "Video", "Sport")
    val hardWords = arrayOf("Beauty", "Report", "Kotlin", "Source")

    var list = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val bundle: Bundle? = intent.extras

        diff = intent.getIntExtra("Diff", -1)
        time = intent.getIntExtra("Time", 60000)

        timer = findViewById(R.id.timer)
        guess = findViewById(R.id.guess)
        guessesLeft = findViewById(R.id.guessesLeft)
        score = findViewById(R.id.score)
        checkButton = findViewById(R.id.checkButton)
        answerButton = findViewById(R.id.answerButton)
        nextButton = findViewById(R.id.nextButton)
        linearLayout = findViewById(R.id.linearLayout)
        answerLayout = findViewById(R.id.answerLetters)

        numOfGuesses(diff)
        displayGuesses(guessesNum)
        generateWords(diff)
        howLong = time
        gameTimer()
    }//onCreate

    fun generateLetter(l : Char, n: Int){
        val idWithIndex : String = l + n.toString();
        val txtName = TextView(this)
        txtName.id = n
        txtName.text = l.toString()
        txtName.textSize = 50F

        list.add(txtName)

        answerLayout.addView(txtName);
        txtName.visibility = View.INVISIBLE

    }

    fun generateWords(difficulty: Int){
        val rand = Random()
        if(difficulty == 0){
            correctAnswer = easyWords[rand.nextInt(easyWords.size - 1)]
        }else if (difficulty == 1){
            correctAnswer = midWords[rand.nextInt(midWords.size - 1)]
        }else if (difficulty == 2){
            correctAnswer = hardWords[rand.nextInt(hardWords.size - 1)]
        }
        correctAnswer.forEachIndexed() { i, char ->generateLetter(char, i) }
    }

    fun checkUserAnswer(){
        userAnswer = guess?.text.toString()
        if(guessesNum == 0){
            Toast.makeText(applicationContext, "out of guesses!", Toast.LENGTH_SHORT ).show()
        }else{
            if(!correctGuessMade){
                guessesNum --
                guessesLeft!!.text = "Guesses Left: " + guessesNum
            }
            if(userAnswer.equals(correctAnswer, ignoreCase = true) && !correctGuessMade){
                Toast.makeText(applicationContext, "Correct!", Toast.LENGTH_SHORT ).show()
                correctGuessMade = true
                correct++
            }
            correctAnswer.forEachIndexed(){ i, char ->
                userAnswer.forEach { userChar ->
                    if (char.equals(userChar, ignoreCase = true)){
                        list[i].visibility = View.VISIBLE
                    }
                }
            }
        }

        if (guessesNum == 0) {
            Toast.makeText(applicationContext, "OUT OF GUESSES!", Toast.LENGTH_SHORT ).show()

        }
        else {
            guessesNum--
            displayGuesses(guessesNum)
        }
    }

    fun next(view: View){
        newWord(diff)
        numOfGuesses(diff)
        correctGuessMade = false
    }

    fun newWord(difficulty: Int){
        val rand = Random()
        if(difficulty == 0){
            correctAnswer = easyWords[rand.nextInt(easyWords.size - 1)]
        }else if (difficulty == 1){
            correctAnswer = midWords[rand.nextInt(midWords.size - 1)]
        }else if (difficulty == 2){
            correctAnswer = hardWords[rand.nextInt(hardWords.size - 1)]
        }
        correctAnswer.forEachIndexed(){i, char ->
            list[i].text = char.toString()
            list[i].visibility = View.INVISIBLE
        }
    }

    fun answer(view: View){
        answer()
    }

    fun answer(){
        list.forEach(){
            txt -> txt.visibility = View.VISIBLE
        }
        Toast.makeText(applicationContext, "You give up!", Toast.LENGTH_SHORT ).show()
    }

    fun numOfGuesses(difficulty : Int) {
        if (difficulty == 0) {
            guessesNum = 5
        }
        else if (difficulty == 1) {
            guessesNum = 4
        }
        else if (difficulty == 2) {
            guessesNum = 3
        }
    }

    fun displayGuesses(guesses : Int) {
        guessesLeft!!.text = "Guesses Left: " + guesses
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
                endGame()
            }//onFinish
        }.start() //CountDown
    }//gameTimer

    fun check (view : View) {
        //TO DO
        checkUserAnswer()
    }//check

    fun endGame() {
        val intent = Intent(this, EndActivity::class.java)
        intent.putExtra("score", correct)
        startActivity(intent)
    }//endGame

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