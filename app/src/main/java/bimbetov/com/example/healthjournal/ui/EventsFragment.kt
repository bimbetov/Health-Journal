package bimbetov.com.example.healthjournal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bimbetov.com.example.healthjournal.R
import bimbetov.com.example.healthjournal.database.Event
import bimbetov.com.example.healthjournal.database.EventViewModal

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EventsFragment : Fragment(), EventClickInterface {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var eventRV: RecyclerView
    lateinit var viewModal: EventViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        eventRV = view?.findViewById(R.id.idRVEvents)!!
        eventRV.layoutManager = LinearLayoutManager(activity)

        val eventRVAdapter = EventRVAdapter(requireContext(), this)
        eventRV.adapter = eventRVAdapter

        viewModal = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            ).get(EventViewModal::class.java)

        viewModal.allEvents.observe(viewLifecycleOwner) { list ->
            eventRVAdapter.submitList(list)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDeleteIconClick(event: Event) {
        viewModal.deleteEvent(event)
        Toast.makeText(requireContext(), "${event.title} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onChecked(event: Event, isChecked: Boolean) {
        viewModal.onChecked(event, isChecked)
    }

    override fun onEventClick(event: Event) {
        findNavController().navigate(EventsFragmentDirections.actionEventsFragmentToAddEventActivity(event))
    }
}