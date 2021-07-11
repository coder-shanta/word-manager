package com.shanto.miah.wm.app.ui.wordlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shanto.miah.wm.app.data.entitys.Word
import com.shanto.miah.wm.app.data.repositories.WordRepo

class WordListViewModel(val wordRepo: WordRepo): ViewModel() {
    fun getWords() : LiveData<List<Word>> {
        return wordRepo.getWords()
    }
}