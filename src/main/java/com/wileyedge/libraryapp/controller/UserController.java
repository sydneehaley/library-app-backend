package com.wileyedge.libraryapp.controller;

import com.wileyedge.libraryapp.entity.Book;
import com.wileyedge.libraryapp.entity.User;
import com.wileyedge.libraryapp.entity.UserBook;
import com.wileyedge.libraryapp.service.UserBookServiceImpl;
import com.wileyedge.libraryapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserBookServiceImpl userBookService;

    //TODO login
    @GetMapping("/{userId}/books")
    public List<Book> getAllBorrowedBooks(@PathVariable int userId){
        return userBookService.getAllByUid(userId);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/signup")
    public User addUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.matchUser(user);
    }

    @PostMapping("/{userId}/{bookId}")
    public void borrowBookByBookId(@PathVariable int bookId, @PathVariable int userId) {
        userBookService.addBookToUser(bookId,userId);
    }

    @DeleteMapping("/{userId}/{bookId}")
    public void returnBookById(@PathVariable int bookId, @PathVariable int userId) {
        userBookService.removeBookFromUser(bookId,userId);
    }
}
