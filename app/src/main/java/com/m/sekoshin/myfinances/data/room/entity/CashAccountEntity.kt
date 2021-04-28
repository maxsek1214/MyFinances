package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.*

@Entity(
        tableName = "cashAccounts_table",
        foreignKeys = [
            ForeignKey(entity = AccountTypeEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("accountType_id")),
            ForeignKey(entity = IssuerEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("issuer_id")),
            ForeignKey(entity = CardTypeEntity::class, parentColumns = arrayOf("id"), childColumns = arrayOf("cardType_id"))
        ],
        indices = [Index(value = arrayOf("issuer_id")), Index(value = arrayOf("cardType_id")), Index(value = arrayOf("accountType_id"))]
)
data class CashAccountEntity(
        @ColumnInfo(name = "accountName")
        var name: String,
        @ColumnInfo(name = "accountType_id", defaultValue = "-1")
        var accountTypeId: Long,
        @ColumnInfo(name = "issuer_id", defaultValue = "-1")
        var issuersId: Long,
        @ColumnInfo(name = "cardType_id", defaultValue = "-1")
        var cardTypeId: Long,
        @ColumnInfo(name = "accountNumber", defaultValue = "0")
        var number: String,
        @ColumnInfo(name = "isAccountCredit", defaultValue = "0")
        var credit: Boolean,
        @ColumnInfo(name = "accountLimit", defaultValue = "0")
        var limit: Float,
        @ColumnInfo(name = "accountBalance", defaultValue = "0")
        var balance: Float) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
