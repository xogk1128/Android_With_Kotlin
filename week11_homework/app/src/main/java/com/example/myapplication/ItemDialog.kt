package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemDialog(private val itemPos : Int = -1) : BottomSheetDialogFragment() {
    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_dialog_layout,  container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editTextName = view.findViewById<EditText>(R.id.editTextName)
        val editTextAddress = view.findViewById<EditText>(R.id.editTextAddress)
        val buttonOk = view.findViewById<Button>(R.id.buttonOK)

        buttonOk.setOnClickListener {
            val item = Item(
                editTextName.text.toString(), editTextAddress.text.toString()
            )
            if(itemPos < 0)
                viewModel.addItem(item)
            else
                viewModel.updateItem(itemPos, item)
            dismiss()
        }
    }
}