package fr.mosho.naturecollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.mosho.naturecollection.*

class PaysageAdapter(
    val context: MainActivity,
    private val paysageList: List<PaysageModele>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<PaysageAdapter.ViewHolder>() {


    //boite pour ranger tout les composants à contrôler
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val image= view.findViewById<ImageView>(R.id.image_item)
        val nomPaysage: TextView? = view.findViewById(R.id.name_item)
        val descriptionPaysage : TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
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

        //recuperer le repository
        val repo = PaysageRepository()

        //utiliser glide pour récuperer l'imagr à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPaysage.imageUrl)).into(holder.image)

        //mettre à jour le nom du paysage
        holder.nomPaysage?.text= currentPaysage.name

        //mettre a jour la description du paysage
        holder.descriptionPaysage?.text = currentPaysage.description

        //vérifier si la plante a été liké ou non
        if(currentPaysage.liked){
            holder.starIcon.setImageResource(R.drawable.ic_like)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unlike)
        }

        //rajouter une interaction sur cette étoile
        holder.starIcon.setOnClickListener{
            // inverser si le bouton est like ou non
            currentPaysage.liked = !currentPaysage.liked
            // mettre à jour l'objet
            repo.updatePaysage(currentPaysage)
        }

        // interaction lors du clic sur une photo
        holder.itemView.setOnClickListener{
            //afficher la popup
            PaysagePopup(this,currentPaysage).show()
        }
    }
}