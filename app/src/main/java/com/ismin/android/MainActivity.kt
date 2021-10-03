package com.ismin.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), BookCreator {

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

    private lateinit var btnCreateBook: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookshelf.addBook(theLordOfTheRings)
        bookshelf.addBook(theHobbit)
        bookshelf.addBook(aLaRechercheDuTempsPerdu)

        btnCreateBook = findViewById(R.id.a_main_btn_create_book)

        btnCreateBook.setOnClickListener {
            displayCreateBook()
        }

        displayBookList()
    }

    private fun displayBookList() {
        btnCreateBook.visibility = View.VISIBLE
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

    private fun displayCreateBook() {
        btnCreateBook.visibility = View.GONE
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = CreateBookFragment.newInstance()
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onBookCreated(book: Book) {
        bookshelf.addBook(book);
        displayBookList()
    }
}