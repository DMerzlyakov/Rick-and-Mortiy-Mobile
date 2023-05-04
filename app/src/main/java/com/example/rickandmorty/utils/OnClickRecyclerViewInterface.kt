package com.example.rickandmorty.utils

/**
 * Вспомогательный интерфейс для обработки нажатий в RecyclerView
 */
interface OnClickRecyclerViewInterface<T> {
    fun onItemClick(item: T, position: Int)
}