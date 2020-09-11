package com.example.todolists.fragments

import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.todolists.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BindingAdapters {

    companion object{
        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate:Boolean)
        {
            view.setOnClickListener{
                if(navigate)
                {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment);
                }
            }
        }



    }

}