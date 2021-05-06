package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.*

@Entity(
    tableName = "stores_table",
    foreignKeys = [
        ForeignKey(
            entity = AliasEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("alias_id")
        )
    ],
    indices = [Index(value = arrayOf("alias_id"))]
)
data class StoreEntity(
    @ColumnInfo(name = "storeName")
    var name: String,
    @ColumnInfo(name = "alias_id", defaultValue = "-1")
    var aliasId: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}
