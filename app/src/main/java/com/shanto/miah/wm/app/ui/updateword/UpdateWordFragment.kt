package com.shanto.miah.wm.app.ui.updateword

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shanto.miah.wm.app.R
import com.shanto.miah.wm.app.data.DB
import com.shanto.miah.wm.app.data.repositories.WordRepo
import com.shanto.miah.wm.app.databinding.FragmentUpdateWordBinding
import com.shanto.miah.wm.app.databinding.FragmentWordListBinding
import com.shanto.miah.wm.app.ui.addword.AddWordViewModel
import com.shanto.miah.wm.app.ui.addword.AddWordViewModelFactory
import com.shanto.miah.wm.app.ui.wordlist.WordListViewModel

class UpdateWordFragment : Fragment() {
    private lateinit var db: DB
    private lateinit var navController: NavController
    private lateinit var binding: FragmentUpdateWordBinding
    private lateinit var viewModel: AddWordViewModel

    val args: UpdateWordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val action = UpdateWordFragmentDirections.actionUpdateWordFragmentToWordListFragment()

        val word = args.word
        db = DB.getDatabase(requireContext())

        navController = findNavController()

        binding = FragmentUpdateWordBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner { lifecycle }

        viewModel = ViewModelProvider(this, AddWordViewModelFactory(WordRepo(db.getWordDao()))).get(
            AddWordViewModel::class.java
        )

        binding.vm = viewModel

        viewModel.setInitState(word)

        binding.wordInput.addTextChangedListener { viewModel.inputChange() }
        binding.meaningInput.addTextChangedListener { viewModel.inputChange() }


        binding.updateButton.setOnClickListener {
            viewModel.updateWord(word.id)
        }


        viewModel.wordUpdated.observe(viewLifecycleOwner, {
            if(it) {
                navController.navigate(action)
                hideKeyboard()
                Toast.makeText(context, "Data Updated Succesfully!", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.wordDeleted.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, "Data Deleted Succesfully!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                navController.navigate(action)
            }
        })

        // Delete word
        binding.deleteButton.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(requireContext())

            dialogBuilder.apply {
                setTitle("Delete")
                setMessage("Do You Realy Want To Delete This Item?")

                setNegativeButton("No", null)
                setPositiveButton("Yes") { dialog, _ ->
                    viewModel.deleteWord(word)
                    dialog.dismiss()
                }
                setCancelable(false)
            }

            dialogBuilder.create().show()
        }

        return binding.root
    }

    fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
    }
}