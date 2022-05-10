package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.items_notes.*

class MainActivity : AppCompatActivity(), INotesAdapter {

    lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        recycler_view.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            ).get(NotesViewModel::class.java)

        viewModel.allNotes.observe(this,
            Observer {list->
                list?.let {
                    adapter.updateList(it)
                }
            })

        addButton.setOnClickListener {
            submitData()

        }

    }

    override fun onItemCLicked(note: Notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(){
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_SHORT).show()
        }
    }
}