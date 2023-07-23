package fr.mosho.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.mosho.naturecollection.MainActivity
import fr.mosho.naturecollection.PaysageModele
import fr.mosho.naturecollection.R
import fr.mosho.naturecollection.adapter.PaysageAdapter
import fr.mosho.naturecollection.adapter.PaysageItemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home,container,false)

        //Créer une liste qui va stocker les paysages
        val paysageList = arrayListOf<PaysageModele>()

        //enregistrer le premier paysage dans notre liste
        paysageList.add(
            PaysageModele(
                name = "Le Mans",
                description = "24h du Mans",
                imageUrl = "https://cdn.pixabay.com/photo/2018/01/25/18/37/porsche-917-3106816_1280.jpg",
                liked= false
        )
        )

        //enregistrer le second paysage dans notre liste
        paysageList.add(
            PaysageModele(
                name = "Le Mans",
                description = "belle voiture ancienne",
                imageUrl = "https://cdn.pixabay.com/photo/2018/02/03/23/17/lenham-le-mans-3128712_1280.jpg",
                liked= false
            )
        )

        //recuperer le recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PaysageAdapter(context,paysageList,R.layout.item_horizontal_paysage)

        //Récupérer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PaysageAdapter(context,paysageList,R.layout.item_vertical_paysage)
        verticalRecyclerView.addItemDecoration(PaysageItemDecoration())

        return view
    }
}