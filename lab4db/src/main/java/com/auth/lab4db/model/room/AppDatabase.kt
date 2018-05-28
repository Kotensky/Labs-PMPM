package com.auth.lab4db.model.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.auth.lab4db.model.enitites.NoteEntity
import com.auth.lab4db.model.room.dao.NoteDao

@Database(entities = [(NoteEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}