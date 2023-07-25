package fr.mosho.naturecollection.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import fr.mosho.naturecollection.MainActivity
import fr.mosho.naturecollection.PaysageRepository
import fr.mosho.naturecollection.R


class AddPaysageFragment(
    private val context : MainActivity
) : Fragment() {

    private var uploadedImage:ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_paysage,container,false)

        //recuperer UploadedImage pour lui associer un composant
        uploadedImage = view.findViewById(R.id.preview_image)

        //recuperer le bouton pour charger l'image
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        //lorsqu'on clique dessus ca ouvre les images du téléphone
        pickupImageButton.setOnClickListener{ pickupImage()}

        return view
    }

    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "select Picture"), 47)

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode== Activity.RESULT_OK){

            // verifier si les données sont nulles
            if (data == null || data.data == null) return

            //recuperer l'image
            val selectedImage = data.data

            //mettre à jour l'apercu de l'image
            uploadedImage?.setImageURI(selectedImage)

            //heberger le bucket
            val repo= PaysageRepository()


        }
    }

}