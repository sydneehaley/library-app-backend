package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.entity.Book;

import java.util.List;

public interface BookService {
    Book addNewBook(Book book);
    List<Book> getAllBooks();
    void saveBook(Book book);
    void fetchAndSaveBooks();
    List<Book> searchFromApi(String text);
    Book getBookById(Integer bid);
    Book updateBook(Book updatedBook);
    Book deleteBookById(Integer bid);
    List<Book> findByText(String text);
}
