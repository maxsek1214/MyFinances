package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.*
import java.util.*

@Entity(
        tableName = "cashTransactions_table",
        foreignKeys = [
            ForeignKey(entity = CashiersCheckEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("cashiersCheck_id")),
            ForeignKey(entity = TransactionCategoryEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("transactionCategory_id")),
            ForeignKey(entity = SourceRecipientEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("sourceRecipient_id"))
        ],
        indices = [Index(value = arrayOf("transactionCategory_id")), Index(value = arrayOf("sourceRecipient_id")), Index(value = arrayOf("cashiersCheck_id"))]
)
data class CashTransactionEntity(
        @ColumnInfo(name = "status")
        var status: Boolean,
        @ColumnInfo(name = "sourceRecipient_id", defaultValue = "-1")
        var sourceRecipientId: Long,
        @ColumnInfo(name = "transactionCategory_id", defaultValue = "-1")
        var transactionCategoryId: Long,
        @ColumnInfo(name = "cashiersCheck_id", defaultValue = "-1")
        var cashiersCheckId: Long,
        @ColumnInfo(name = "dateTime")
        var dateTime: Date,
        @ColumnInfo(name = "amount")
        var amount: Float,
        @ColumnInfo(name = "comment")
        var comment: String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
