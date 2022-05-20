/*
package bimbetov.com.example.healthjournal.ui

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import bimbetov.com.example.healthjournal.R
import bimbetov.com.example.healthjournal.database.Event
import bimbetov.com.example.healthjournal.database.EventDatabase
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddEventFragment : BaseFragment() {

    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_add_event, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val saveBtn = view?.findViewById<Button>(R.id.button_save)
        saveBtn?.setOnClickListener {
            val noteTitle = view?.findViewById<EditText>(R.id.edit_text_title).toString().trim()
            val noteBody = view?.findViewById<EditText>(R.id.edit_text_note).toString().trim()

            if (noteTitle.isEmpty()){
                view?.findViewById<EditText>(R.id.edit_text_title)!!.error = "title required"
                view?.findViewById<EditText>(R.id.edit_text_title)!!.requestFocus()
                 return@setOnClickListener
            }
            if (noteBody.isEmpty()){
                view?.findViewById<EditText>(R.id.edit_text_note)!!.error = "note required"
                view?.findViewById<EditText>(R.id.edit_text_note)!!.requestFocus()
                return@setOnClickListener
            }

            launch {
                val event = Event(noteTitle, noteBody)
                context?.let {
                    EventDatabase(it).getEventDao().addEvent(event)
                    it.toast("Note Saved")
                }
            }


        }
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddEventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}*/
