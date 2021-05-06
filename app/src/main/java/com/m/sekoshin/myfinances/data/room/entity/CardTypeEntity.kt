package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardTypes_table")
data class CardTypeEntity(
    @ColumnInfo(name = "cardTypeName")
    var name: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
