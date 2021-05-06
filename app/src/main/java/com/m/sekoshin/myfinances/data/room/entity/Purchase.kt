package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class Purchase(
    @Embedded
    val purchaseEntity: PurchaseEntity,
    @Relation(
        parentColumn = "cashiersCheck_id",
        entityColumn = "id",
        entity = CashiersCheckEntity::class
    )
    val checkEntity: CashiersCheckEntity,
    @Relation(parentColumn = "store_id", entityColumn = "id", entity = StoreEntity::class)
    val storeEntity: StoreEntity,
    @Relation(parentColumn = "good_id", entityColumn = "id", entity = GoodEntity::class)
    val goodEntity: GoodEntity
)
