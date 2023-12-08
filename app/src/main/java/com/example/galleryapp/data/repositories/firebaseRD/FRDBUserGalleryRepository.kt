package com.example.galleryapp.data.repositories.firebaseRD

import com.example.galleryapp.utils.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

interface FRDBUserGalleryRepository {

    suspend fun getSaved(uid: String, onResult: (Resource<List<Int>>) -> Unit)

}

class FRDBUserGalleryRepositoryImpl @Inject constructor(
    private val databaseRef: DatabaseReference
): FRDBUserGalleryRepository {
    override suspend fun getSaved(uid: String, onResult: (Resource<List<Int>>) -> Unit) {
        databaseRef
            .child(uid)
            .child(FRBDUser.SAVED.value)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list: ArrayList<Int> = ArrayList()
                    for (ds in snapshot.children) {
                        val model = ds.getValue(Int::class.java)

                        if (model != null) {
                            list.add(model)
                        }

                        onResult.invoke(
                            Resource.Success(list.toList())
                        )
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

}
