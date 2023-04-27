package com.example.rickandmorty.character.presentation.list.recycler_view

/**
 * Вспомогательный интерфейс для обработки нажатий в RecyclerView
 */
interface OnClickRecyclerViewInterface <T> {
    fun onItemClick(item: T, position: Int)
}