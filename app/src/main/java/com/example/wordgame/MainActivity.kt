package com.example.wordgame

import android.content.Intent
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
    var letters = 4
    var checked : RadioButton? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }//onCreateOptionsMenu

    fun playGame(view: View?) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("Letters", letters)
        intent.putExtra("Diff", difficulty)
        startActivity(intent)
    }//playGame

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.four -> letters = 4
            R.id.five -> letters = 5
            R.id.six -> letters = 6
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