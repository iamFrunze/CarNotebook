package com.byfrunze.carnotebook.providers

import android.util.Log
import com.byfrunze.carnotebook.models.Brand
import com.byfrunze.carnotebook.presenters.ChooseAutoPresenter
import com.google.firebase.firestore.FirebaseFirestore

class ChooseAutoProvider(val presenter: ChooseAutoPresenter) {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun loadBrands() {
        val arrayOfBrands = ArrayList<Brand>()
        db.collection("brands").get()
            .addOnSuccessListener { docs ->
                if (docs != null) {
                    for (doc in docs) {
                        arrayOfBrands.add(
                            Brand(
                                name = doc.data["name"].toString(),
                                logo = doc.data["logo"].toString(),
                                site = doc.data["site"].toString()
                            )

                        )

                    }

                    presenter.completeLoading(arrayOfBrands)
                }

            }
            .addOnFailureListener { fail ->
                presenter.errorLoad(fail.localizedMessage)
            }


    }
}