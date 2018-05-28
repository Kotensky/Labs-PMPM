package com.auth.lab4db.interfaces

import com.auth.lab4db.model.enitites.NoteEntity

interface IDB {

    fun loadNoteList() : ArrayList<NoteEntity>

    fun getNoteFromList(id: Long?): NoteEntity?

    fun insertNote(text: String)

    fun deleteNote(id: Long?)

    fun updateNote(id: Long, text: String)

    fun addOnNeedUpdateDataListener(key: Int, listener: OnNeedUpdateDataListener)

    fun removeOnNeedUpdateDataListener(key: Int)

}