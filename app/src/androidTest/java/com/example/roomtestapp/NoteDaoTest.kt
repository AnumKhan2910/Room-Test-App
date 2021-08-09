package com.example.roomtestapp

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var noteRoomDatabase: NoteRoomDatabase
    private lateinit var noteDao: NoteDao

    @Before
    fun setup(){
        noteRoomDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),NoteRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        noteDao = noteRoomDatabase.noteDao()
    }

    @After
    fun tearDown(){
        noteRoomDatabase.close()
    }

    @Test
    fun insertNote() = runBlockingTest {
        val note = Note("002", "My Test Note")

        noteDao.insert(note)

        val allNotes : List<Note> = noteDao.getAllNotes().getOrAwaitValue()

        assertThat(allNotes[0].id).isEqualTo(note.id)
    }


    @Test
    fun deleteNote() = runBlockingTest {
        val note = Note("002", "My Test Note")

        noteDao.insert(note)
        noteDao.deleteNote(note)

        val allNotes : List<Note> = noteDao.getAllNotes().getOrAwaitValue()

        assertThat(allNotes).isEmpty()
    }

}