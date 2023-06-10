package com.wileyedge.libraryapp.controller;


import com.wileyedge.libraryapp.entity.Book;
import com.wileyedge.libraryapp.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {

    @Autowired
    BookServiceImpl bookService;
    //add so I can commit
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search/{text}")
    public List<Book> findByBookByText(@PathVariable String text) {
        return bookService.findByText(text);
    }

    @GetMapping("/download/{text}")
    public List<Book> searchBookByText(@PathVariable String text) {
        return bookService.searchFromApi(text);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
    }
}
