package fr.mosho.naturecollection

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import fr.mosho.naturecollection.PaysageRepository.Singleton.databaseReF
import fr.mosho.naturecollection.PaysageRepository.Singleton.paysageList
import java.util.*

class PaysageRepository {

    object Singleton {
        //donner le lien pour acceder au bucket
        private val BUCKET_URL: String = "gs://paysagecollection.appspot.com"


        // se connecter à la référence "paysages"
        val databaseReF = FirebaseDatabase.getInstance().getReference("Paysages")

        //se connecter à notre espace de stockage
       // val storageReference = FirebaseStorage.getInstance().getReference(BUCKET_URL)

        //créer une liste qui va contenir nos paysages
        val paysageList = arrayListOf<PaysageModele>()
    }

    fun updateData(callback:() -> Unit) {
        // absorber les données depuis la database -> liste de plantes
        databaseReF.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) { // recuperer valeur en liste
                //retirer les anciennes
                paysageList.clear()
                // récolter la liste
                for(ds in snapshot.children)
                {
                    // construire objet
                    val paysage = ds.getValue(PaysageModele::class.java)

                    // verifier que le paysage n'est pas null
                    if (paysage!= null) {
                        //ajouter la plante à notre liste
                        paysageList.add(paysage)
                    }
                }
                // actionner le callback
                callback()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }



    // mettre à jour un objet plante en bdd
    fun updatePaysage(paysage: PaysageModele) = databaseReF.child(paysage.id).setValue(paysage)

    //supprimer une plante de la base
    fun deletePaysage(paysage : PaysageModele) = databaseReF.child(paysage.id).removeValue()


}