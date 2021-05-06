package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aliases_table")
data class AliasEntity(
    @ColumnInfo(name = "aliasName")
    var name: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
