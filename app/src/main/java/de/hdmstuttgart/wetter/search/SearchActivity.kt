package de.hdmstuttgart.wetter.Search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.hdmstuttgart.wetter.R

/** The Search Fragment holds an XML-Constraint-Layout which contains the
 * FragmentContainerView with the name de.hdmstuttgart.wetter.Search.SearchFragment.
 * So the GUI-Components plus the logic for searching the weather for
 * the certain town are embedded in the Search Fragment.
 * */
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}