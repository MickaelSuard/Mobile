package fr.mosho.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.mosho.naturecollection.MainActivity
import fr.mosho.naturecollection.PaysageRepository
import fr.mosho.naturecollection.PaysageRepository.Singleton.paysageList
import fr.mosho.naturecollection.R
import fr.mosho.naturecollection.adapter.PaysageAdapter
import fr.mosho.naturecollection.adapter.PaysageItemDecoration

class CollectionFragment(private val context : MainActivity) : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_collection,container,false)

        //recuperer le recyclerview
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = PaysageAdapter(context, paysageList.filter { it.liked } ,R.layout.item_vertical_paysage)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(PaysageItemDecoration())
        return view
    }
}