package com.example.galleryapp.data.repositories.folder

import com.example.galleryapp.data.models.Folder
import com.example.galleryapp.utils.Resource
import com.example.galleryapp.utils.enums.FolderType
import com.google.firebase.Firebase
import com.google.firebase.storage.storage

class FoldersRepositoryImpl : FoldersRepository {
    override suspend fun getPrivateFolders(
        authorUid: String?,
        onResult: (Resource<List<Folder>>) -> Unit
    ) {
       getFolder(authorUid, FolderType.PRIVATE, onResult)
    }

    private suspend fun getFolder(authorUid: String?,
                                  folderType: FolderType,
                                  onResult: (Resource<List<Folder>>) -> Unit) {
        val folders: MutableList<Folder> = mutableListOf()
        val storageRef = Firebase.storage.reference
        storageRef.child("users/${authorUid}/images/${folderType.type}")
            .listAll().addOnSuccessListener { listResult ->
                var downloadedCount = 0

                for (item in listResult.items) {
                    val folderName = item.name
                    folders.add(Folder(folderName))

                    downloadedCount++
                    if (downloadedCount == listResult.items.size) {
                        onResult.invoke(Resource.Success(folders))
                    }
                }
            }
    }


    override suspend fun getPublicFolders(authorUid: String?, onResult: (Resource<List<Folder>>) -> Unit) {
        getFolder(authorUid, FolderType.PUBLIC, onResult)
    }
}