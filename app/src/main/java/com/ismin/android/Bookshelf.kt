package com.ismin.android

class Bookshelf {

    private val storage = HashMap<String, Book>()

    fun addBook(aBook: Book) {
        storage[aBook.title] = aBook
    }

    fun getBook(title: String): Book? {
        return storage[title]
    }

    fun getAllBooks(): ArrayList<Book> {
        return ArrayList(storage.values.sortedBy { it.title })
    }

    fun getBooksOf(author: String): List<Book> {
        return storage
            .filterValues { it.author == author }
            .values
            .sortedBy { it.title }
            .toList();
    }

    fun getTotalNumberOfBooks(): Int {
        return storage.size;
    }

    fun clear() {
        storage.clear()
    }

}
