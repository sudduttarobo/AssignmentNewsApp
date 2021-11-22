package com.example.demonews.room

import androidx.room.TypeConverter
import com.example.demonews.model.entity.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return  source.name
    }

    @TypeConverter
    fun toSource(name: String):Source{
        return Source(name = name,id = name)
    }
}