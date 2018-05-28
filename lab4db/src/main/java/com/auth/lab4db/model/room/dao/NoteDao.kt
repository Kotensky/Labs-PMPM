package com.auth.lab4db.model.room.dao

import android.arch.persistence.room.*
import com.auth.lab4db.model.enitites.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<NoteEntity>

}