package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.*

@Entity(
    tableName = "goods_table",
    foreignKeys = [
        ForeignKey(
            entity = AliasEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("alias_id")
        ),
        ForeignKey(
            entity = GoodCategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("goodsCategory_id")
        )
    ],
    indices = [Index(value = arrayOf("alias_id")), Index(value = arrayOf("goodsCategory_id"))]
)
data class GoodEntity(
    @ColumnInfo(name = "storeName")
    var name: String,
    @ColumnInfo(name = "alias_id", defaultValue = "-1")
    var aliasId: Long,
    @ColumnInfo(name = "goodsCategory_id", defaultValue = "-1")
    var goodsCategoryId: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
