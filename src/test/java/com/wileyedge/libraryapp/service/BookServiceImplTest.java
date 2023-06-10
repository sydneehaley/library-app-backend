package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void findBookServiceTest() {
        Book aBook = new Book();
        aBook.setTitle("For Testing");
        aBook.setAuthors("fake authors");
        aBook.setDescription("fake description");
        bookService.addNewBook(aBook);

        Book returnBook = bookService.getBookById(41);
        assertNotNull(returnBook);
        assertEquals("For Testing", returnBook.getTitle());
    }

    @Test
    public void bookNotFoundServiceTest() {
        Book notFound = bookService.getBookById(99);
        assertNull(notFound);
    }

    @Test
    public void findAllBooksServiceTest() {
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
        assertEquals(40, books.size());
    }

    @Test
    public void updateBookServiceTest() {
        Book book = new Book();
        book.setBid(41);
        book.setTitle("Updated Book Title");
        book.setAuthors("Updated Book Authors");
        book.setDescription("Updated Book Description");

        Book upBook = bookService.updateBook(book);
        assertNotNull(upBook);
        assertEquals((Integer)41, upBook.getBid());
        assertEquals("Updated Book Title", upBook.getTitle());
        assertEquals("Updated Book Authors", upBook.getAuthors());
        assertEquals("Updated Book Description", upBook.getDescription());
    }

    @Test
    public void bookNotUpdatedServiceTest() {
        Book book = new Book();
        book.setBid(114);
        book.setTitle("Updated Book Title");
        book.setAuthors("Updated Book Authors");
        book.setDescription("Updated Book Description");

        Book upBook = bookService.updateBook(book);
        assertNotNull(upBook);
        assertEquals("Book NOT found. Could not update", upBook.getTitle());
    }

    @Test
    public void bookAddServiceTest() {
        Book book = new Book();
        book.setTitle("New Book Title");
        book.setAuthors("New Book Authors");
        Book newBook = bookService.addNewBook(book);
        assertNotNull(newBook);
        assertEquals("New Book Title", newBook.getTitle());
        assertEquals("New Book Authors", newBook.getAuthors());
    }

    @Test
    public void bookNoAddServiceTest() {
        Book book = new Book();
        book.setTitle("");
        Book newBook = bookService.addNewBook(book);
        assertNotNull(newBook);
        assertEquals("Book NOT added. Title empty.", newBook.getTitle());
    }

    @Test
    public void bookDeleteServiceTest() {
        Book book = new Book();
        book.setTitle("");
        Book newBook = bookService.addNewBook(book);
        assertNotNull(newBook);
        assertEquals("Book NOT added. Title empty.", newBook.getTitle());
    }
}