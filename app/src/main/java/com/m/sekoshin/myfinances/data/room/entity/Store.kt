package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class Store(
        @Embedded
        val storeEntity: StoreEntity,
        @Relation(parentColumn = "alias_id", entityColumn = "id", entity = AliasEntity::class)
        val aliasEntity: AliasEntity
)
