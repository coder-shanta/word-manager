package com.shanto.miah.wm.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shanto.miah.wm.app.data.entitys.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word ORDER BY id DESC")
    fun getWords() : LiveData<List<Word>>

    @Insert
    suspend fun insertWord(word: Word) : Long

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)
}