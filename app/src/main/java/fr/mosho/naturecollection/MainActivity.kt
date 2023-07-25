package fr.mosho.naturecollection

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.ActionMenuView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.mosho.naturecollection.fragments.AddPaysageFragment
import fr.mosho.naturecollection.fragments.CollectionFragment
import fr.mosho.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this),R.string.home_page_title)

        val  navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)

        navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this),R.string.home_page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this),R.string.collection_page_title)
                    return@setOnItemSelectedListener true
                }
                R.id.add_page -> {
                    loadFragment(AddPaysageFragment(this),R.string.add_page_title)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // charger notre Paysage repository
        val repo = PaysageRepository()

        //actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text= resources.getString(string)

        // mettre à jour la liste des paysages
        repo.updateData{
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null) // pas de retour sur le composant
            transaction.commit() // envoyer les changements
        }
    }
}