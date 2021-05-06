package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.*

@Entity(
    tableName = "flowOfFunds_table",
    foreignKeys = [
        ForeignKey(
            entity = CashAccountEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("cashAccount_id")
        ),
        ForeignKey(
            entity = CashTransactionEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("cashTransaction_id")
        )
    ],
    indices = [Index(value = arrayOf("cashAccount_id")), Index(value = arrayOf("cashTransaction_id"))]
)
data class FlowOfFundEntity(
    @ColumnInfo(name = "cashAccount_id", defaultValue = "-1")
    var cashAccountId: Long,
    @ColumnInfo(name = "cashTransaction_id", defaultValue = "-1")
    var cashTransactionId: Long,
    @ColumnInfo(name = "balance")
    var balance: Float
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
