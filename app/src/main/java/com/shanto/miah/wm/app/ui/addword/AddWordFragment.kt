package com.shanto.miah.wm.app.ui.addword

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.shanto.miah.wm.app.R
import com.shanto.miah.wm.app.data.DB
import com.shanto.miah.wm.app.data.repositories.WordRepo
import com.shanto.miah.wm.app.databinding.FragmentAddWordBinding

class AddWordFragment : Fragment() {

    private lateinit var db: DB
    private lateinit var navController: NavController
    private lateinit var binding: FragmentAddWordBinding
    private lateinit var viewModel: AddWordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val action = AddWordFragmentDirections.actionAddWordFragmentToWordListFragment()

        db = DB.getDatabase(requireContext())

        navController = findNavController()

        binding = FragmentAddWordBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner { lifecycle }

        viewModel = ViewModelProvider(this, AddWordViewModelFactory(WordRepo(db.getWordDao()))).get(
            AddWordViewModel::class.java
        )

        binding.vm = viewModel

        binding.wordInput.addTextChangedListener { viewModel.inputChange() }
        binding.meaningInput.addTextChangedListener { viewModel.inputChange() }

        binding.saveButton.setOnClickListener {
            viewModel.saveWord()
        }

        viewModel.wordSaved.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, "Data Saved Succesfully!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                navController.navigate(action)
            }
        })

        return binding.root
    }

    fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
    }
}