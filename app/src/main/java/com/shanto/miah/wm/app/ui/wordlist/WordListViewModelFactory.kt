package com.shanto.miah.wm.app.ui.wordlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shanto.miah.wm.app.data.repositories.WordRepo

class WordListViewModelFactory(val wordRepo: WordRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WordListViewModel(wordRepo) as T
    }
}