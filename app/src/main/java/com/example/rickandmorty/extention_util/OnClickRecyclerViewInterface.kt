package com.example.rickandmorty.extention_util

/**
 * Вспомогательный интерфейс для обработки нажатий в RecyclerView
 */
interface OnClickRecyclerViewInterface <T> {
    fun onItemClick(item: T, position: Int)
}