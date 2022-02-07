package com.example.wordgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class EndActivity : AppCompatActivity() {

    var score : Int = 0
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        result = findViewById(R.id.finalScore)
        score = intent.getIntExtra("score",0)

        displayScore(score)

    }

    fun displayScore(scr: Int){
        result.text = score.toString()
    }

    fun playAgain(view : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}