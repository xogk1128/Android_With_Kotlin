package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class home : Fragment(R.layout.home_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            val viewModel : MyViewModel by activityViewModels()
            viewModel.increaseCount()
            findNavController().navigate(R.id.action_home_to_nav1)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.countLiveData.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.textView).text = it.toString()
        }

    }
}

class nav1 : Fragment(R.layout.nav1_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            val viewModel : MyViewModel by activityViewModels()
            viewModel.increaseCount()
            findNavController().navigate(R.id.action_nav1_to_nav2)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.countLiveData.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.textView).text = it.toString()
        }

    }
}

class nav2 : Fragment(R.layout.nav2_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            val viewModel : MyViewModel by activityViewModels()
            viewModel.increaseCount()
            findNavController().navigate(R.id.action_nav2_to_home)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.countLiveData.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.textView).text = it.toString()
        }

    }
}