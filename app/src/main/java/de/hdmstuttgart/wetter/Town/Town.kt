package de.hdmstuttgart.wetter.Town

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Town (
    @PrimaryKey(autoGenerate = true) val key: Int = 0,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("description") val description: String,
    //temperature
    @ColumnInfo("temperature") val temperature: String,
    @ColumnInfo("windtempo") val windtempo: String,
    // we can add an icon from the folder res.drawable.
    //@SerializedName("icon") val icon: String? = null,
    )