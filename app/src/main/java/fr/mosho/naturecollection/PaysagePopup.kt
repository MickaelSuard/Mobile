package fr.mosho.naturecollection

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.mosho.naturecollection.adapter.PaysageAdapter

class PaysagePopup(
    private val adapter: PaysageAdapter,
    private val currentPaysage : PaysageModele
    ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_paysage_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateStar(button: ImageView){
        if (currentPaysage.liked){
            button.setImageResource(R.drawable.ic_like)
        }
        else {
            button.setImageResource(R.drawable.ic_unlike)
        }
    }

    private fun setupStarButton() {
        // recuperer
        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)

        //interaction
        starButton.setOnClickListener{
            currentPaysage.liked = !currentPaysage.liked
            val repo = PaysageRepository()
            repo.updatePaysage(currentPaysage)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            //supprimer le paysage de la base
            val repo = PaysageRepository()
            repo.deletePaysage(currentPaysage)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            //fermer la fenetre popup
            dismiss()
        }
    }

    private fun setupComponents() {
        //actualiser l'image de la plante
        val paysageImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPaysage.imageUrl)).into(paysageImage)

        //actualiser le nom
        findViewById<TextView>(R.id.popup_plant_name).text = currentPaysage.name

        // actualiser la description
        findViewById<TextView>(R.id.popup_description_subtitle).text = currentPaysage.description

        // actualiser le continent
        findViewById<TextView>(R.id.popup_continent_subtitle).text = currentPaysage.continent

        // actualiser la marque
        findViewById<TextView>(R.id.popup_marque_subtitle).text = currentPaysage.marque
    }

}