package com.example.rickandmorty.presentation

/**
 * Вспомогательный интерфейс для обработки нажатий в RecyclerView
 */
interface OnClickRecyclerViewInterface <T> {
    fun onItemClick(item: T, position: Int)
}