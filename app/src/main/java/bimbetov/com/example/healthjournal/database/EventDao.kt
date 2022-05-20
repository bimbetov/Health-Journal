package bimbetov.com.example.healthjournal.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM eventsTable ORDER by id ASC")
    fun getAllEvents(): LiveData<List<Event>>

}