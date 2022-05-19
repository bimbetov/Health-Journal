package bimbetov.com.example.healthjournal.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "eventsTable")
@Parcelize
data class Event(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val eventDescription: String,
    @ColumnInfo(name = "timestamp") val timeStamp: Long,
    @ColumnInfo(name = "hour") val hour: Int? = null,
    @ColumnInfo(name = "minute") val minute: Int? = null,
    @ColumnInfo(name = "isChecked") val isChecked: Boolean = false,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) : Parcelable