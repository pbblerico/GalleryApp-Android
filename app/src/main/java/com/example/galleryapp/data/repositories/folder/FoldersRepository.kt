package com.example.galleryapp.data.repositories.folder

import com.example.galleryapp.data.models.Folder
import com.example.galleryapp.utils.Resource

interface FoldersRepository {
    suspend fun getPrivateFolders(
        authorUid: String?,
        onResult: (Resource<List<Folder>>) -> Unit
    )


    suspend fun getPublicFolders(authorUid: String?, onResult: (Resource<List<Folder>>) -> Unit)
}