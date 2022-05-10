package com.example.notesapp

import android.content.Context
import android.renderscript.Int4
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context: Context,val listner: INotesAdapter)
    : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    val allNodes = ArrayList<Notes>()


    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.texter)!!
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.items_notes,parent,false))

        viewHolder.deleteButton.setOnClickListener {
            listner.onItemCLicked(allNodes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNode = allNodes[position]
        holder.textView.text = currentNode.text
    }

    override fun getItemCount(): Int {
        return allNodes.size
    }

    fun updateList(newList: List<Notes>){
        allNodes.clear()
        allNodes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesAdapter{
    fun onItemCLicked(note:Notes)
}