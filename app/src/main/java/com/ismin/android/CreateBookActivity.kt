package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

const val BOOK_TO_CREATE_KEY = "BOOK_TO_CREATE_KEY"

class CreateBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_book)

        findViewById<Button>(R.id.a_create_book_btn_save).setOnClickListener {
            saveBook()
        }
    }

    private fun saveBook() {
        val author = findViewById<TextInputEditText>(R.id.a_create_book_edt_author).text.toString()
        val title = findViewById<TextInputEditText>(R.id.a_create_book_edt_title).text.toString()
        val date = findViewById<TextInputEditText>(R.id.a_create_book_edt_date).text.toString()
        val book = Book(title, author, date);

        val returnIntent = Intent()
        returnIntent.putExtra(BOOK_TO_CREATE_KEY, book)
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}