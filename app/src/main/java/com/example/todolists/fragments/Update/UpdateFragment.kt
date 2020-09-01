package com.example.todolists.fragments.Update

import android.app.AlertDialog
import android.os.Bundle
import android.renderscript.RenderScript
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolists.R
import com.example.todolists.database.model.Priority
import com.example.todolists.database.model.TodoData
import com.example.todolists.database.viewmodel.SharedViewModelFragment
import com.example.todolists.database.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.row_layout.view.*


class UpdateFragment : Fragment() {
    //  private val args by navArgs<UpdateFragmentArgs>()
    private val args by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModelFragment by viewModels()
    private val mTodoViewModelFragment: TodoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        view.current_title_et.setText(args.currentItem.title)
        view.current_description_et.setText(args.currentItem.descriptionn)
        view.current_priorities_spinner.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        view.current_priorities_spinner.onItemSelectedListener = mSharedViewModel.listener
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_list_fragment_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.update_menu) {
            updateValues()
        }

        else if(item.itemId==R.id.delete_menu){
            deleteItem()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTodoViewModelFragment.deleteData(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: ${args.currentItem.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.title}'?")
        builder.setMessage("Are you sure you want to remove '${args.currentItem.title}'?")
        builder.create().show()
    }

    private fun updateValues() {

        val title = current_title_et.text.toString()
        val description = current_description_et.text.toString()
        val getPriority = current_priorities_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            // Update Current Item
            val updatedItem = TodoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mTodoViewModelFragment.updateData(updatedItem)
            Toast.makeText(requireContext(),"Updated  successfully",Toast.LENGTH_LONG).show()
                //Navigate
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
            else
        {
            Toast.makeText(requireContext(),"values should not be empty",Toast.LENGTH_LONG).show()

        }
    }
}