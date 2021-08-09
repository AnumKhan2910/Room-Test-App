package com.example.roomtestapp


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(var context: Context,
                   var itemClickListener: ItemClickListener) : ListAdapter<Note, RecyclerView.ViewHolder>(DiffCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder){
            holder.repoName.text = currentList[position].mNote

            holder.view.setOnClickListener {
                itemClickListener.onItemClicked(currentList[position])
            }
        }
    }

    class RepoViewHolder(var view : View) : RecyclerView.ViewHolder(view){
        val repoName: TextView = view.findViewById(R.id.name)
    }


    class DiffCallBack : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }
}