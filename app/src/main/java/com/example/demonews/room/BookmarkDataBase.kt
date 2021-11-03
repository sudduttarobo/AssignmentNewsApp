package com.example.demonews.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BookmarkTableModel::class), version = 1, exportSchema = false)
abstract class BookmarkDataBase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDAO

    companion object {

        @Volatile
        private var INSTANCE: BookmarkDataBase? = null

        fun getDatabaseClient(context: Context): BookmarkDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, BookmarkDataBase::class.java, "NewsBookmarks")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!

            }
        }
    }
}