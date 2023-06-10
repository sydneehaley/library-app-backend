package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.entity.Book;
import com.wileyedge.libraryapp.entity.UserBook;

import java.util.List;

public interface UserBookService {
    void addBookToUser(Integer bid, Integer uid);
    void removeBookFromUser(Integer bid, Integer uid);
    List<Book> getAllByUid(Integer uid);
}
