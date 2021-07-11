package com.shanto.miah.wm.app.ui.addword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanto.miah.wm.app.data.entitys.Word
import com.shanto.miah.wm.app.data.repositories.WordRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWordViewModel(private val wordRepo: WordRepo): ViewModel() {
    var word = MutableLiveData("")
    var meaning = MutableLiveData("")
    var dataValid = MutableLiveData(false)
    var wordSaved = MutableLiveData(false)
    val wordUpdated = MutableLiveData(false)
    val wordDeleted = MutableLiveData(false)

    fun inputChange() {
        if(word.value!!.isEmpty()) {
            dataValid.value = false
        } else dataValid.value = meaning.value!!.isNotEmpty()
    }


    // Save Word
    fun saveWord() {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepo.insertWord(Word(0, word.value!!, meaning.value!!))
            wordSaved.postValue(true)
        }
    }

    // Update Word
    fun updateWord(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepo.updateWord(Word(id, word.value!!, meaning.value!!))
            wordUpdated.postValue(true)
        }
    }

    // Delete Word
    fun deleteWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepo.deleteWord(word)
            wordDeleted.postValue(true)
        }
    }

    fun setInitState(w: Word) {
        word.value = w.word
        meaning.value = w.meaning
    }
}