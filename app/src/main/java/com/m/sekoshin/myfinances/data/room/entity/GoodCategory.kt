package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class GoodCategory(
    @Embedded
    val goodCategoryEntity: GoodCategoryEntity,
    @Relation(parentColumn = "parent_id", entityColumn = "id", entity = GoodCategoryEntity::class)
    val children: List<GoodCategoryEntity>
)
