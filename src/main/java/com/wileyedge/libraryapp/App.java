package com.wileyedge.libraryapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import com.wileyedge.libraryapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class App {

    private final BookService bookService;

    @Autowired
    public App(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void populateDatabase() {
        bookService.fetchAndSaveBooks();
    }
}
