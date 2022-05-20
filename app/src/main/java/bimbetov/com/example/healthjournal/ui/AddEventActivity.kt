package bimbetov.com.example.healthjournal.ui


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import bimbetov.com.example.healthjournal.R
import bimbetov.com.example.healthjournal.database.Event
import bimbetov.com.example.healthjournal.database.EventViewModal
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddEventActivity : AppCompatActivity() {
    lateinit var eventTitleEdit : EditText
    lateinit var eventDescriptionEdit : EditText
    lateinit var addUpdateBtn : Button
    lateinit var textTime : TextView
    lateinit var viewModal: EventViewModal

    private val navArgs by navArgs<AddEventActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_event)

        // on below line we are initialing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EventViewModal::class.java)

        viewModal.selectedTime.observe(this, ::setupTime)

        // on below line we are initializing all our variables.
        eventTitleEdit = findViewById(R.id.edit_text_title)
        eventDescriptionEdit = findViewById(R.id.edit_text_note)
        addUpdateBtn = findViewById(R.id.button_save)
        textTime = findViewById(R.id.edit_time)

        textTime.setOnClickListener {
            onTimeClicked()
        }

        // on below line we are getting data passed via an intent.
        val event = navArgs.event
        if (event != null) {
            // on below line we are setting data to edit text.
            val noteTitle = event.title
            val noteDescription = event.eventDescription
            val hour = event.hour
            val minute = event.minute
            val time = if (hour == null || minute == null) null else hour to minute
            viewModal.setSelectedTime(time)
            addUpdateBtn.text = "Обновить"
            eventTitleEdit.setText(noteTitle)
            eventDescriptionEdit.setText(noteDescription)
        } else {
            addUpdateBtn.text = "Сохранить"
            viewModal.setSelectedTime(null)
        }
        addUpdateBtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val noteTitle = eventTitleEdit.text.toString()
            val noteDescription = eventDescriptionEdit.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.
            val hour = viewModal.selectedTime.value?.first
            val minute = viewModal.selectedTime.value?.second
            if (event != null) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val updatedNote = Event(noteTitle, noteDescription, Date().time, hour, minute, id = event.id)
                    viewModal.updateEvent(updatedNote)
                    Toast.makeText(this, "Запись обновлена..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    // if the string is not empty we are calling a
                    // add note method to add data to our room database.
                    viewModal.addEvent(Event(noteTitle, noteDescription, Date().time, hour, minute))
                    Toast.makeText(this, "$noteTitle добавлено", Toast.LENGTH_LONG).show()
                }
            }
            this.finish()
        }
    }

    private fun setupTime(time: Pair<Int, Int>?) {
        textTime.text = time?.getTimeText() ?: "Выберите время"
    }

    private fun onTimeClicked() {
        val dialog = MaterialTimePicker.Builder()
            .apply {
                val time = viewModal.selectedTime.value ?: return@apply
                setHour(time.first)
                setMinute(time.second)
            }
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        dialog.addOnPositiveButtonClickListener {
            viewModal.setSelectedTime(dialog.hour, dialog.minute)
        }
        dialog.show(supportFragmentManager, "timePicker")
    }
}