package com.shanto.miah.wm.app.ui.wordlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shanto.miah.wm.app.R
import com.shanto.miah.wm.app.data.DB
import com.shanto.miah.wm.app.data.entitys.Word
import com.shanto.miah.wm.app.data.repositories.WordRepo
import com.shanto.miah.wm.app.databinding.FragmentWordListBinding

class WordListFragment : Fragment(), ClickListner {

    private lateinit var db: DB
    private lateinit var navController: NavController
    private lateinit var binding: FragmentWordListBinding
    private lateinit var viewModel: WordListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()

        db = DB.getDatabase(requireContext())

        binding = FragmentWordListBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner { lifecycle }

        viewModel =
            ViewModelProvider(this, WordListViewModelFactory(WordRepo(db.getWordDao()))).get(
                WordListViewModel::class.java
            )

        viewModel.getWords().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {

                var lm = LinearLayoutManager(context)
                lm.stackFromEnd = true

                binding.wordListRecyclerview.apply {
                    adapter = WordListAdapter(it, this@WordListFragment)
                    layoutManager = lm
                }
            }
        })

        binding.fab.setOnClickListener {
            val action = WordListFragmentDirections.actionWordListFragmentToAddWordFragment()
            navController.navigate(action)
        }

        return binding.root
    }

    override fun onItemLongPress(word: Word, position: Int): Boolean {
        val action = WordListFragmentDirections.actionWordListFragmentToUpdateWordFragment(word)
        navController.navigate(action)
        return true
    }
}