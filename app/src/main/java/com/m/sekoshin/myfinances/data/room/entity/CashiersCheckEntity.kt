package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.*

@Entity(tableName = "cashiersChecks_table")
data class CashiersCheckEntity(
        @ColumnInfo(name = "fn")
        var fn: Int,
        @ColumnInfo(name = "fd")
        var fd: Int,
        @ColumnInfo(name = "fpd")
        var fpd: Int,
        @ColumnInfo(name = "check_url")
        var url: String,
        @ColumnInfo(name = "status")
        var status: Boolean) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
