package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accountTypes_table")
data class AccountTypeEntity(
        @ColumnInfo(name = "typeName")
        var name: String,
        @ColumnInfo(name = "isTypeCash")
        var isCash: Boolean,
        @ColumnInfo(name = "isTypeCard")
        var isCard: Boolean) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
