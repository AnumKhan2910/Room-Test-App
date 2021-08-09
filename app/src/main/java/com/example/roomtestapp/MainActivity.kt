package com.example.roomtestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomtestapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : NoteViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter : NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        notesAdapter = NotesAdapter(this, object : ItemClickListener{
            override fun onItemClicked(note: Note) {
                viewModel.deleteNote(note)
            }
        })

        binding.recyclerView.adapter = notesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getAllNotes().observe(this,
            { t -> notesAdapter.submitList(t) })

        binding.btn.setOnClickListener {

            val noteText : String = binding.editText.text.toString()

            if (noteText.isNotEmpty()){
                val note = Note(UUID.randomUUID().toString(), noteText)
                viewModel.insertNotes(note)
            }

            /*val intent = Intent(this,SecondaryActivity::class.java)
            startActivity(intent)*/
        }
    }
}