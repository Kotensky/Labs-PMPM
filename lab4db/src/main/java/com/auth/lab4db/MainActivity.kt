package com.auth.lab4db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.auth.lab4db.interfaces.IDB
import com.auth.lab4db.interfaces.OnNeedUpdateDataListener
import com.auth.lab4db.model.enitites.NoteEntity
import com.auth.lab4db.model.room.dao.NoteDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IDB {

    private var noteDao: NoteDao? = null
    private val noteList: ArrayList<NoteEntity> = ArrayList()
    private val needUpdateDataListenerHashMap = HashMap<Int, OnNeedUpdateDataListener>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        noteDao = App.database?.noteDao()
        loadNoteList()
        initPager()
    }

    override fun loadNoteList(): ArrayList<NoteEntity> {
        noteList.clear()
        noteList.addAll(noteDao?.getAllNotes() as ArrayList<NoteEntity>)
        return noteList
    }

    override fun insertNote(text: String) {
        noteDao?.insertNote(NoteEntity(null, text))
        Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
        notifyUpdateDataListeners()
    }

    override fun deleteNote(id: Long?) {
        val note = getNoteFromList(id)
        if (note != null) {
            noteDao?.deleteNote(note)
            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
        }
        notifyUpdateDataListeners()
    }

    override fun updateNote(id: Long, text: String) {
        val note = getNoteFromList(id)
        if (note != null) {
            note.text = text
            noteDao?.updateNote(note)
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
        }
        notifyUpdateDataListeners()
    }

    override fun getNoteFromList(id: Long?): NoteEntity? {
        for (note in noteList) {
            if (note.id == id) {
                return note
            }
        }
        Toast.makeText(this, "Note #$id doesn't exist", Toast.LENGTH_SHORT).show()
        return null
    }

    override fun addOnNeedUpdateDataListener(key: Int, listener: OnNeedUpdateDataListener) {
        needUpdateDataListenerHashMap[key] = listener
    }

    override fun removeOnNeedUpdateDataListener(key: Int) {
        needUpdateDataListenerHashMap.remove(key)
    }

    private fun notifyUpdateDataListeners() {
        for (entry in needUpdateDataListenerHashMap) {
            entry.value.onNeedUpdateData()
        }
    }

    private fun initPager() {
        pager_main.adapter = PagerAdapter(supportFragmentManager)
        pager_main.offscreenPageLimit = 3
        tab_layout_main.setupWithViewPager(pager_main)
    }
}
