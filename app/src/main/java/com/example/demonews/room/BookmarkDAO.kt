package com.example.demonews.room

import androidx.room.*

@Dao
interface BookmarkDAO {
    @Query("SELECT * FROM NewsBookmarks")
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
    fun updateTodo(vararg bookmarkTableModel: BookmarkTableModel)
}