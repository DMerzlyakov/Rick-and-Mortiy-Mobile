package com.example.rickandmorty.episode.data.list.local

import androidx.room.TypeConverter

class ListIntConverter {
    @TypeConverter
    fun fromString(value: String): List<Int> =
        if (value == "") emptyList() else value.split(',').map { it.toInt() }
    
    @TypeConverter
    fun toString(list: List<Int>): String =
        if (list.isEmpty()) "" else list.joinToString(",")
}