package com.auth.lab4db

import android.app.Application
import android.arch.persistence.room.Room
import com.auth.lab4db.model.room.AppDatabase


class App : Application() {

    companion object {
        var instance: App? = null
        var database: AppDatabase? = null
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .allowMainThreadQueries()
                .build()
    }
}