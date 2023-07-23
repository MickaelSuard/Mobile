package fr.mosho.naturecollection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.mosho.naturecollection.R

class PaysageAdapter : RecyclerView.Adapter<PaysageAdapter.ViewHolder>() {
    //boite pour ranger tout les composants à contrôler
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val image= view.findViewById<ImageView>(R.id.image_item)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horizontal_paysage,parent,false)
        return ViewHolder(view)
    }

    //renvoyer combien d'item affiché dynamiquement
    override fun getItemCount(): Int=5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}
}