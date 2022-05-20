package bimbetov.com.example.healthjournal.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map


class EventRepository(private val eventDao: EventDao) {

    val allEvents: LiveData<List<Event>> = eventDao.getAllEvents().map {
        it.sortedBy { event ->
            event.hour?.times(60)?.plus(event.minute ?: 0)
        }.sortedBy { event -> event.isChecked }
    }

    suspend fun insert(event: Event){
        eventDao.insert(event)
    }

    suspend fun delete(event: Event){
        eventDao.delete(event)
    }

    suspend fun update(event: Event){
        eventDao.update(event)
    }
}