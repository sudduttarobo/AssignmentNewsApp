package com.example.demonews.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.demonews.model.entity.Article

@Database(
        entities = [Article::class],
        version = 1)

@TypeConverters(Converters::class)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {

        @Volatile
        private var instance: ArticleDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDataBase(context).also { instance = it }
        }

        private fun createDataBase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        ArticleDataBase::class.java,
                        "article_db.db")
                        .build()
        /* fun getDatabaseClient(context: Context): ArticleDataBase {

             if (INSTANCE != null) return INSTANCE!!

             synchronized(this) {

                 INSTANCE = Room
                         .databaseBuilder(context, ArticleDataBase::class.java, "NewsBookmarks")
                         .fallbackToDestructiveMigration()
                         .allowMainThreadQueries()
                         .build()

                 return INSTANCE!!

             }
         }*/
    }
}