package de.hdmstuttgart.movietracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.hdmstuttgart.movietracker.Search.SearchActivity
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    fun onSearchMovieClick(view: View){
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}