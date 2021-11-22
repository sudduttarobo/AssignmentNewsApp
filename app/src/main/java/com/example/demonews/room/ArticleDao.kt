package com.example.demonews.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demonews.model.entity.Article

@Dao
interface ArticleDao {
    /*@Query("SELECT * FROM NewsBookmarks")
    fun getAll(): List<BookmarkTableModel>

//    @Query("SELECT * FROM article")
//    fun getAllArticle(): List<ArticalTable>

    @Query("SELECT * FROM NewsBookmarks")
    fun findByTitle(): BookmarkTableModel

    @Insert
    fun insertAll(vararg bookmarkTableModel: BookmarkTableModel)

//    @Insert
//    fun insertAllArticle(vararg article: ArticalTable)

    @Delete
    fun delete(vararg bookmarkTableModel: BookmarkTableModel)

    @Update
    fun updateTodo(vararg bookmarkTableModel: BookmarkTableModel)*/

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("SELECT * FROM articles" )
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}