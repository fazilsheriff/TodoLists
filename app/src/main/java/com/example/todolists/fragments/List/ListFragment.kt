package com.example.todolists.fragments.List

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolists.R
import com.example.todolists.database.model.TodoData
import com.example.todolists.database.viewmodel.SharedViewModelFragment
import com.example.todolists.database.viewmodel.TodoViewModel
import com.example.todolists.databinding.FragmentListBinding
import com.example.todolists.fragments.List.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar


class ListFragment : Fragment() {
    private val mToDoViewModel: TodoViewModel by viewModels()
    private val mSharedViewModelFragment: SharedViewModelFragment by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val mSharedViewModel: SharedViewModelFragment by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // Inflate the layout for this fragment
//        val view=inflater.inflate(R.layout.fragment_list, container, false)

        // Setup RecyclerView
        setupRecyclerview()



        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            mSharedViewModelFragment.checkIfDatabaseEmpty(it)
            adapter.setData(it)
        })

//        mSharedViewModelFragment.emptyDataBase.observe(viewLifecycleOwner, Observer {
//
//            ShowDataBaseEmptyViews(it)
//        })



        setHasOptionsMenu(true)
        return binding.root
    }


    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = adapter.dataList[viewHolder.adapterPosition]
                // Delete Item
                mToDoViewModel.deleteData(deletedItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedData(viewHolder.itemView,deletedItem,viewHolder.position)
//                Toast.makeText(requireContext(),"Success fully removed: '${deletedItem.title}'",Toast.LENGTH_LONG).show()
                // Restore Deleted Item
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    private fun setupRecyclerview() {
        val recyclerView = binding.recylerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager =LinearLayoutManager(requireActivity())
        swipeToDelete(recyclerView)
    }


    private fun restoreDeletedData(view: View, deletedItem: TodoData,position:Int) {
        val snackBar = Snackbar.make(
            view, "Deleted '${deletedItem.title}'",
            Snackbar.LENGTH_LONG
        )
        snackBar.setAction("Undo") {
            mToDoViewModel.insertData(deletedItem)
            adapter.notifyItemRemoved(position)

        }
        snackBar.show()
    }

//    private fun ShowDataBaseEmptyViews(emptyDataBase: Boolean) {
//            if(emptyDataBase)
//            {
//                view?.txtnoData?.visibility=View.VISIBLE
//                view?.ivNoData?.visibility=View.VISIBLE
//            }
//
//        else
//            {
//                view?.txtnoData?.visibility=View.INVISIBLE
//                view?.ivNoData?.visibility=View.INVISIBLE
//            }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete_all_menu) {
           deleteAllValues()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllValues() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteAllDatas()
            Toast.makeText(
                requireContext(),
                "Successfully Removed all items: ",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete All items?")
        builder.setMessage("Are you sure you want to remove all items?")
        builder.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}