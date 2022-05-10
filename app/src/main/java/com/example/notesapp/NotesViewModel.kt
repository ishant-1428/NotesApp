package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Appendable

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val allNotes : LiveData<List<Notes>>
    private val respository : NotesRepository

    init {
        val database = NotesRoomDatabase.getDatabase(application).getNoteDao()
        respository = NotesRepository(database)
        allNotes = respository.allNodes
    }

    fun deleteNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        respository.delete(notes)
    }

    fun insertNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO){
        respository.insert(notes)
    }
}