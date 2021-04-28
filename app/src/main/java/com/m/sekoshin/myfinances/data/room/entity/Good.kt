package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class Good(
        @Embedded
        val goodEntity: GoodEntity,
        @Relation(parentColumn = "alias_id", entityColumn = "id", entity = AliasEntity::class)
        val aliasEntity: AliasEntity,
        @Relation(parentColumn = "goodsCategory_id", entityColumn = "id", entity = GoodCategoryEntity::class)
        val goodCategoryEntity: GoodCategoryEntity
)
