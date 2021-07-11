package com.shanto.miah.wm.app.data.repositories

import androidx.lifecycle.LiveData
import com.shanto.miah.wm.app.data.dao.WordDao
import com.shanto.miah.wm.app.data.entitys.Word

class WordRepo(val wordDao: WordDao) {
    fun getWords(): LiveData<List<Word>> {
        return wordDao.getWords()
    }

    suspend fun insertWord(word: Word): Long {
        return wordDao.insertWord(word)
    }

    suspend fun updateWord(word: Word) {
        return wordDao.updateWord(word)
    }

    suspend fun deleteWord(word: Word) {
        return wordDao.deleteWord(word)
    }
}