package com.dicoding.tourismapp

import com.dicoding.tourismapp.core.domain.model.Tourism

object DataDummy {
    fun generateDummyTourism(): List<Tourism> {
        val items: MutableList<Tourism> = arrayListOf()
        for (i in 0..100) {
            val tourism = Tourism(
                tourismId = i.toString(),
                name = "name + $i",
                description = "description + $i",
                address = "address + $i",
                latitude = i.toDouble(),
                longitude = i.toDouble(),
                like = i,
                image = "image + $i",
                isFavorite = i % 2 == 0
            )
            items.add(tourism)
        }
        return items
    }
}