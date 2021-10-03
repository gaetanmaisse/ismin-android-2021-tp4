package com.ismin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val bookshelf = Bookshelf()
    private val adapter = BookAdapter(bookshelf.getAllBooks())

    private val startForResult = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val book = result.data?.getSerializableExtra(BOOK_TO_CREATE_KEY) as Book
            bookshelf.addBook(book);
            Log.d(TAG, "Number of books:" + bookshelf.getTotalNumberOfBooks())

            adapter.refreshData(bookshelf.getAllBooks())
            adapter.notifyDataSetChanged();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCreateBook = findViewById<FloatingActionButton>(R.id.a_main_btn_create_book)

        btnCreateBook.setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startForResult.launch(intent)
        }

        val rcvBooks = findViewById<RecyclerView>(R.id.a_main_rcv_books)
        rcvBooks.layoutManager = LinearLayoutManager(this)
        rcvBooks.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_menu_delete -> {
                bookshelf.clear()
                adapter.refreshData(bookshelf.getAllBooks())
                adapter.notifyDataSetChanged();
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }
}