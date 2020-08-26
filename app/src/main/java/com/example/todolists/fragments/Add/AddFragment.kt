package com.example.todolists.fragments.Add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolists.R
import com.example.todolists.database.model.Priority
import com.example.todolists.database.model.TodoData
import com.example.todolists.database.viewmodel.SharedViewModelFragment
import com.example.todolists.database.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private val mToDoViewModel: TodoViewModel by viewModels()
    private val sharedViewModel: SharedViewModelFragment by viewModels()

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view=inflater.inflate(R.layout.fragment_add, container, false)

    setHasOptionsMenu(true)
//         view.priorities_spinner.onItemSelectedListener = sharedViewModel.listener
            view.priorities_spinner.onItemSelectedListener=sharedViewModel.listener

         return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_menu){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if(validation){
            // Insert Data to Database
            val newData = TodoData(
                0,
                mTitle,
                sharedViewModel.parsePriority(mPriority),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_list_fragment_menu,menu)

    }

}