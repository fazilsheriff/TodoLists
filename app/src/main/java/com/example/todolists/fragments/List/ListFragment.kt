package com.example.todolists.fragments.List

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolists.R
import com.example.todolists.database.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private val mToDoViewModel: TodoViewModel by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView = view.recylerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager =LinearLayoutManager(requireActivity())

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
        })
        view.floatingActionBarAddData.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        view.listParent.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
    }

}