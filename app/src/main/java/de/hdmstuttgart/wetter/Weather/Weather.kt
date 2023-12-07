package de.hdmstuttgart.movietracker.Weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "uid") val uid: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "poster") val poster: String,
)