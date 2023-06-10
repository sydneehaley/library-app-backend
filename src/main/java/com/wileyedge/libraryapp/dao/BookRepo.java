package com.wileyedge.libraryapp.dao;

import com.wileyedge.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingIgnoreCase(String text);
    List<Book> findByAuthorsContainingIgnoreCase(String text);
    List<Book> findByDescriptionContainingIgnoreCase(String text);
}
