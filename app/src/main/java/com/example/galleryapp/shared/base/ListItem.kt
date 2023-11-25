package com.example.galleryapp.shared.base

interface ListItem {
    enum class Type(value: Int) {TypeA(0), TypeB(1) }
    fun getListItemType(): Int
}

data class ItemA(val textA: String): ListItem {
    override fun getListItemType(): Int {
        return ListItem.Type.TypeA.ordinal
    }
}

