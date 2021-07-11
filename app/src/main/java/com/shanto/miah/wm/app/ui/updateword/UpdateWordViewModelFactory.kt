package com.shanto.miah.wm.app.ui.updateword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shanto.miah.wm.app.data.repositories.WordRepo

class UpdateWordViewModelFactory(val wordRepo: WordRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpdateWordViewModel(wordRepo) as T
    }
}