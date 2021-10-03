package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_BOOKS = "books"

class BookListFragment : Fragment() {
    private lateinit var books: ArrayList<Book>
    private lateinit var adapter : BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val argBooks = requireArguments().getSerializable(ARG_BOOKS) as ArrayList<Book>?
        books = argBooks ?: ArrayList()
        adapter = BookAdapter(books)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_list, container, false)

        val rcvBooks = view.findViewById<RecyclerView>(R.id.f_book_list_rcv_books)
        rcvBooks.layoutManager = LinearLayoutManager(context)
        rcvBooks.adapter = adapter

        return view;
    }

    companion object {
        @JvmStatic
        fun newInstance(books: ArrayList<Book>) =
            BookListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_BOOKS, books)
                }
            }
    }
}