package com.example.wordgame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class EndActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    var score : Int = 0
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        result = findViewById(R.id.finalScore)
        score = intent.getIntExtra("score",0)

        play()
        displayScore(score)

    }//onCreate

    fun play() {
        mediaPlayer = MediaPlayer.create(this, R.raw.finish)
        mediaPlayer.start()
    }//play

    fun displayScore(scr: Int){
        result.text = score.toString()
    }//displayScore

    fun playAgain(view : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }//playAgain
}