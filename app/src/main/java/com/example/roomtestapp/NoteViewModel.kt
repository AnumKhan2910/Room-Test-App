package com.example.roomtestapp

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    var noteRoomDatabase : NoteRoomDatabase = NoteRoomDatabase.DatabaseProvider.getInstance(application)
    var noteDao: NoteDao = noteRoomDatabase.noteDao()

    var liveData: LiveData<List<Note>> = noteDao.getAllNotes()

    fun getAllNotes() : LiveData<List<Note>> {
        return liveData
    }

    fun deleteNote(note : Note){
        viewModelScope.launch {
            note.mNote = "Anum"
            noteDao.updateNote(note)

            viewModelScope.launch(Dispatchers.Main){
                Toast.makeText(getApplication(), "Note Deleted", Toast.LENGTH_SHORT).show()

            }
        }
    }

    @SuppressLint("ShowToast")
    fun insertNotes(note: Note){
        viewModelScope.launch {
            noteDao.insert(note)

            viewModelScope.launch(Dispatchers.Main){
                    Toast.makeText(getApplication(), "Note Saved", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        noteRoomDatabase.close()
    }
}