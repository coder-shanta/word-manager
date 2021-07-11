package com.shanto.miah.wm.app.ui.wordlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shanto.miah.wm.app.R
import com.shanto.miah.wm.app.data.entitys.Word

class WordListAdapter(val words: List<Word>, val clickListner: ClickListner): RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val wordText: TextView
        val meaningText: TextView
        val parent: View

        init {
            wordText = view.findViewById(R.id.word_text)
            meaningText = view.findViewById(R.id.meaning_text)
            parent = view
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            wordText.text = words.get(position).word
            meaningText.text = words.get(position).meaning
            itemView.setOnLongClickListener {
                return@setOnLongClickListener clickListner.onItemLongPress(words.get(position), position)
            }
        }
    }

    override fun getItemCount(): Int {
        return words.size
    }
}

interface ClickListner {
    fun onItemLongPress(word: Word, position: Int) : Boolean
}