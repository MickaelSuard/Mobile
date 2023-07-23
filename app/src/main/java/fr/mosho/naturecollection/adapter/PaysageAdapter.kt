package fr.mosho.naturecollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.mosho.naturecollection.MainActivity
import fr.mosho.naturecollection.PaysageModele
import fr.mosho.naturecollection.R

class PaysageAdapter(
    private val context: MainActivity,
    private val paysageList: List<PaysageModele>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<PaysageAdapter.ViewHolder>() {


    //boite pour ranger tout les composants à contrôler
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val image= view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId,parent,false)
        return ViewHolder(view)
    }

    //renvoyer combien d'item affiché dynamiquement
    override fun getItemCount(): Int=paysageList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les informatons du paysage
        val currentPaysage = paysageList[position]

        //utiliser glide pour récuperer l'imagr à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPaysage.imageUrl)).into(holder.image)

    }
}