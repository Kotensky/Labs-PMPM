package com.auth.lab4db

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auth.lab4db.model.enitites.NoteEntity
import kotlinx.android.synthetic.main.note_list_item.view.*

class NoteListAdapter(private val noteList: ArrayList<NoteEntity>) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false))

    override fun getItemCount(): Int = noteList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView?.id_txt?.text = "# ${noteList[position].id}"
        holder.itemView?.text_txt?.text = noteList[position].text
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}