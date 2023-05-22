package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val viewModel by viewModels<MyViewModel>()

        viewModel.addItem(Item("James", "Seoul"))
        viewModel.addItem(Item("tom", "Inchon"))
        viewModel.addItem(Item("tom", "Inchon"))
        viewModel.addItem(Item("tom", "Inchon"))

        val adapter = CustomAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            ItemDialog().show(supportFragmentManager, "ItemDialog")
        }

        viewModel.itemListData.observe(this) {
            adapter.notifyDataSetChanged()
        }

        viewModel.itemClickEvent.observe(this) {
            ItemDialog(it).show(supportFragmentManager, "ItemDialog")
        }

        registerForContextMenu(recyclerView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.ctx_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.delete -> viewModel.deleteItem(viewModel.itemLongClick)
            R.id.edit -> viewModel.itemClickEvent.value = viewModel.itemLongClick
            else -> return false
        }
        return true
    }
}