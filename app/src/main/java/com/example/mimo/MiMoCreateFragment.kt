package com.example.mimo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.mimo.database.MiMoDatabase

class MiMoCreateFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.mimo_create_fragment,
            container, false
        )
        val add = view.findViewById<Button>(R.id.add_btn)
        add.setOnClickListener {
            val t = view.findViewById<EditText>(R.id.title).text.toString()
//            val t = view.findViewById<EditText>(R.id.create_text).text.toString()
            val c = view.findViewById<EditText>(R.id.contents).text.toString()

            db = MiMoDatabase.getInstance(requireContext())

            val data = Data(title = t, content = c)

        }
        return view
    }
}