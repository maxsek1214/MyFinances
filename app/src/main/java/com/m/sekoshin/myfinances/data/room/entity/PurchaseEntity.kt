package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.*

@Entity(
    tableName = "purchases_table",
    foreignKeys = [
        ForeignKey(
            entity = CashiersCheckEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("cashiersCheck_id")
        ),
        ForeignKey(
            entity = StoreEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("store_id")
        )
    ],
    indices = [Index(value = arrayOf("cashiersCheck_id")), Index(value = arrayOf("store_id"))]
)
data class PurchaseEntity(
    @ColumnInfo(name = "cashiersCheck_id", defaultValue = "-1")
    var cashiersCheckId: Long,
    @ColumnInfo(name = "store_id", defaultValue = "-1")
    var storeId: Long,
    @ColumnInfo(name = "good_id", defaultValue = "-1")
    var goodId: Long,
    @ColumnInfo(name = "price")
    var price: Float,
    @ColumnInfo(name = "quantity")
    var quantity: Float,
    @ColumnInfo(name = "totalPrice")
    var total: Float
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
