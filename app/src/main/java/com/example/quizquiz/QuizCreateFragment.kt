package com.example.quizquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.quizquiz.database.Quiz
import com.example.quizquiz.database.QuizDatabase

class QuizCreateFragment: Fragment() {
    lateinit var db: QuizDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.quiz_create_fragment,
            container,
            false
        )
        val add = view.findViewById<Button>(R.id.add_btn)
        add.setOnClickListener {
            val q = view.findViewById<EditText>(R.id.t_question).text.toString()
            val a = view.findViewById<EditText>(R.id.t_answer).text.toString()
            val c = view.findViewById<EditText>(R.id.t_category).text.toString()

            db = QuizDatabase.getInstance(requireContext())

            val quiz = Quiz(type="ox", question = q, answer = a, category = c)
            Thread {
                db.quizDAO().insert(quiz)
            }.start()

        }
        return view
    }
}