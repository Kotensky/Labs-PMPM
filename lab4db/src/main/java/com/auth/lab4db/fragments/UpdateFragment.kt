package com.auth.lab4db.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.auth.lab4db.R
import com.auth.lab4db.interfaces.IDB
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        update_btn.setOnClickListener {
            if (id_edt.text.isNullOrEmpty()) {
                Toast.makeText(context, "Enter id of your note", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (text_edt.text.isNullOrEmpty()){
                Toast.makeText(context, "Enter text of your note", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (activity != null && activity is IDB) {
                (activity as IDB).updateNote(id_edt.text.toString().toLong(), text_edt.text.toString())
            }
        }
    }
}