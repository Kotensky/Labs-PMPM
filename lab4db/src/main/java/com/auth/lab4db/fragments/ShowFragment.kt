package com.auth.lab4db.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auth.lab4db.NoteListAdapter
import com.auth.lab4db.R
import com.auth.lab4db.interfaces.IDB
import com.auth.lab4db.interfaces.OnNeedUpdateDataListener
import com.auth.lab4db.model.enitites.NoteEntity
import kotlinx.android.synthetic.main.fragment_show.*


class ShowFragment : Fragment(), OnNeedUpdateDataListener {

    private val noteList: ArrayList<NoteEntity> = ArrayList()
    private var noteListAdapter : NoteListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null && activity is IDB) {
            (activity as IDB).addOnNeedUpdateDataListener(this.hashCode(), this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onNeedUpdateData()
        noteListAdapter = NoteListAdapter(noteList)
        note_recycler.layoutManager = LinearLayoutManager(context)
        note_recycler.adapter = noteListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        if (activity != null && activity is IDB) {
            (activity as IDB).removeOnNeedUpdateDataListener(this.hashCode())
        }
    }


    override fun onNeedUpdateData() {
        if (activity != null && activity is IDB) {
            noteList.clear()
            noteList.addAll((activity as IDB).loadNoteList())
            noteListAdapter?.notifyDataSetChanged()
        }
    }

}