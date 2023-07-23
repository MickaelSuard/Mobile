package fr.mosho.naturecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.mosho.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //injecter le fragment dans notre boite (fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,HomeFragment(this))
        transaction.addToBackStack(null) // pas de retour sur le composant
        transaction.commit() // envoyer les changements
    }
}