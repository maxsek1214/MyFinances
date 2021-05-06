package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "transactionCategories_table", indices = [Index(value = arrayOf("parent_id"))])
data class TransactionCategoryEntity(
    @ColumnInfo(name = "categoryName")
    var name: String,
    @ColumnInfo(name = "income_expense")
    var incomeExpense: Boolean,
    @ColumnInfo(name = "parent_id", defaultValue = "-1")
    var parent: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
