package com.example.roomtestapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1 )
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    object DatabaseProvider {
        private var database: String = "notes_database"
        private var sInstance: NoteRoomDatabase? = null

        fun getInstance(context: Context) : NoteRoomDatabase {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    database
                ).build()
            }

            return sInstance!!
        }
    }

}