package com.leonett.breakingbadcharacters.data.local.db

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun stringToStringList(value: String?): List<String>? {
        return value?.split(LIST_ITEM_DELIMITER)
    }

    @TypeConverter
    fun stringListToString(list: List<String>?): String? {
        return list?.joinToString(LIST_ITEM_DELIMITER)
    }

    @TypeConverter
    fun stringToIntList(value: String?): List<Int>? {
        return value?.split(LIST_ITEM_DELIMITER)?.map { it.toInt() }
    }

    @TypeConverter
    fun intListToString(list: List<Int>?): String? {
        return list?.joinToString(LIST_ITEM_DELIMITER)
    }

    companion object {
        private const val LIST_ITEM_DELIMITER = "|"
    }

}