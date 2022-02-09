package com.example.wordgame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var difficulty = 0
    var time = 60000
    var checked : RadioButton? = null
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }//onCreateOptionsMenu

    fun playGame(view: View?) {
        mediaPlayer = MediaPlayer.create(this, R.raw.start)
        mediaPlayer.start()
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("Time", time)
        intent.putExtra("Diff", difficulty)
        startActivity(intent)
    }//playGame

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.one -> time = 60000
            R.id.two -> time = 120000
            R.id.three -> time = 180000
        }
        return true
    } //end onOptionsItemSelected

    fun howHard(view : View) {
        checked = (view as RadioButton)
        when(view.getId()) {
            R.id.easyLevel -> difficulty = 0
            R.id.mediumLevel -> difficulty = 1
            R.id.hardLevel -> difficulty = 2
        }//when
    }//howHard


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }//onCreate

}//MainActivity