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
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val bookshelf = Bookshelf()
    private val theLordOfTheRings = Book(
        title = "The Lord of the Rings",
        author = "J. R. R. Tolkien",
        date = "1954-02-15"
    )

    private val theHobbit = Book(
        title = "The Hobbit",
        author = "J. R. R. Tolkien",
        date = "1937-09-21"
    )
    private val aLaRechercheDuTempsPerdu = Book(
        title = "À la recherche du temps perdu",
        author = "Marcel Proust",
        date = "1927"
    )

    private val startForResult = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val book = result.data?.getSerializableExtra(BOOK_TO_CREATE_KEY) as Book
            bookshelf.addBook(book);
            Log.d(TAG, "Number of books:" + bookshelf.getTotalNumberOfBooks())
            displayBookList()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookshelf.addBook(theLordOfTheRings)
        bookshelf.addBook(theHobbit)
        bookshelf.addBook(aLaRechercheDuTempsPerdu)

        val btnCreateBook = findViewById<FloatingActionButton>(R.id.a_main_btn_create_book)

        btnCreateBook.setOnClickListener {
            val intent = Intent(this, CreateBookActivity::class.java)
            startForResult.launch(intent)
        }

        displayBookList()
    }

    private fun displayBookList() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = BookListFragment.newInstance(bookshelf.getAllBooks())
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
        fragmentTransaction.commit()
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
                displayBookList()
                true
            }
            // If we got here, the user's action was not recognized.
            else -> super.onOptionsItemSelected(item)
        }
    }
}