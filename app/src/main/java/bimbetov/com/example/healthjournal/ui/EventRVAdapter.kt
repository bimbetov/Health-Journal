package bimbetov.com.example.healthjournal.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bimbetov.com.example.healthjournal.R
import bimbetov.com.example.healthjournal.database.Event
import com.google.android.material.checkbox.MaterialCheckBox
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

class EventRVAdapter(
    val context: Context,
    val listener: EventClickInterface,
) : ListAdapter<Event, EventRVAdapter.ViewHolder>(Callback()) {

    private val allEvents get() = currentList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val eventTV = itemView.findViewById<TextView>(R.id.idTVEventTitle)
        val timestampTV = itemView.findViewById<TextView>(R.id.idTVTimeStamp)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idIVDelete)
        val timeTV = itemView.findViewById<TextView>(R.id.edit_time)
        val checkbox = itemView.findViewById<MaterialCheckBox>(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = allEvents[position]
        holder.eventTV.text = item.title
        val sdf = SimpleDateFormat("HH:mm dd MMM, yyyy")
        val date = sdf.format(Date(item.timeStamp))
        holder.timestampTV.text = "$date"
        holder.timeTV.text = if (item.hour == null || item.minute == null) ""
        else (item.hour to item.minute).getTimeText()

        holder.checkbox.isChecked = item.isChecked

        holder.deleteTV.setOnClickListener{
            listener.onDeleteIconClick(item)
        }

        holder.itemView.setOnClickListener {
            listener.onEventClick(item)
        }
        holder.checkbox.setOnClickListener {
            listener.onChecked(item, !item.isChecked)
        }

    }

    override fun getItemCount(): Int {
        return allEvents.size
    }

    class Callback: DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }
}

interface EventClickInterface {
    fun onEventClick(event: Event)
    fun onDeleteIconClick(event: Event)
    fun onChecked(event: Event, isChecked: Boolean = false)
}