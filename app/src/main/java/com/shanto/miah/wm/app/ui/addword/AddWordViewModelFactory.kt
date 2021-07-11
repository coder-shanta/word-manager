package com.shanto.miah.wm.app.ui.addword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shanto.miah.wm.app.data.repositories.WordRepo

class AddWordViewModelFactory(val wordRepo: WordRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddWordViewModel(wordRepo) as T
    }
}