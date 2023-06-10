package com.wileyedge.libraryapp.dao;

import com.wileyedge.libraryapp.entity.Book;
import com.wileyedge.libraryapp.entity.User;
import com.wileyedge.libraryapp.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookDao extends JpaRepository<UserBook, Integer> {
    UserBook findByUserAndBook(User user, Book book);
    List<UserBook> findByUser_Uid(Integer uid);
}
