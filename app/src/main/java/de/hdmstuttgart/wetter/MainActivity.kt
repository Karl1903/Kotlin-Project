package de.hdmstuttgart.wetter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.hdmstuttgart.wetter.search.SearchActivity

/** With the start of the application the user gets into the Screen of the Main Activity.
 * There is one button with the text "Search Weather for Town".
 * The Button click navigates into the Search Activity.
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //Navigates to the Search Activity.
    fun onSearchTownClick(view: View){
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}