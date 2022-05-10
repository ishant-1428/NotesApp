package com.example.notesapp

import androidx.lifecycle.LiveData


class NotesRepository(private val noteDao : NoteDao) {

    val allNodes : LiveData<List<Notes>> = noteDao.getAllNotes()

    suspend fun insert(note: Notes){
        noteDao.insert(note)
    }

    suspend fun delete(note: Notes){
        noteDao.delete(note)
    }

}