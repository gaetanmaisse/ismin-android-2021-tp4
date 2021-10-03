package com.ismin.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class CreateBookFragment : Fragment() {

    private lateinit var bookCreatorListener: BookCreator
    private lateinit var authorEdt: EditText
    private lateinit var titleEdt: EditText
    private lateinit var dateEdt: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_book, container, false)

        authorEdt = rootView.findViewById<EditText>(R.id.f_create_book_edt_author)
        titleEdt = rootView.findViewById<EditText>(R.id.f_create_book_edt_title)
        dateEdt = rootView.findViewById<EditText>(R.id.f_create_book_edt_date)

        rootView.findViewById<Button>(R.id.f_create_book_btn_save).setOnClickListener {
            saveBook()
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BookCreator) {
            bookCreatorListener = context
        } else {
            throw RuntimeException("$context must implement MyActivityCallback")
        }
    }

    private fun saveBook() {
        val author = authorEdt.text.toString()
        val title = titleEdt.text.toString()
        val date = dateEdt.text.toString()
        val book = Book(title, author, date)
        bookCreatorListener.onBookCreated(book)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateBookFragment()
    }
}