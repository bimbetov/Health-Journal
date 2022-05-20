package bimbetov.com.example.healthjournal.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModal(application: Application) : AndroidViewModel(application) {
    val allEvents: LiveData<List<Event>>
    val repository: EventRepository

    val selectedTime = MutableLiveData<Pair<Int, Int>?>()

    init {
        val dao = EventDatabase.getDatabase(application).getEventDao()
        repository = EventRepository(dao)
        allEvents = repository.allEvents
    }

    fun deleteEvent(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(event)
    }

    fun updateEvent(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(event)
    }

    fun addEvent(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(event)
    }

    fun setSelectedTime(hour: Int, minute: Int) {
        selectedTime.value = hour to minute
    }

    fun setSelectedTime(time: Pair<Int, Int>?) {
        selectedTime.value = time
    }

    fun onChecked(event: Event, isChecked: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(event.copy(isChecked = isChecked))
    }

}