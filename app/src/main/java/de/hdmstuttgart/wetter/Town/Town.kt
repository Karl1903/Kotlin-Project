package de.hdmstuttgart.wetter.Town

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Town (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "townID") val townID: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description-weather") val description: String,
    @ColumnInfo(name = "degrees-temperature") val degrees: String,
)