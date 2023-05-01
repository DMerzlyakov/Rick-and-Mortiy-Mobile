//package com.example.rickandmorty.character.data.list.local
//
//class LocationDetailConverter {
//    @TypeConverter
//    fun fromLocationDetail(locationDetail: LocationDetail): String {
//        return "${locationDetail.id},${locationDetail.name}"
//    }
//
//    @TypeConverter
//    fun toLocationDetail(value: String): LocationDetail {
//        val parts = value.split(",")
//        return LocationDetail(parts[0].toInt(), parts[1])
//    }
//}