package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.entity.Book;
import com.wileyedge.libraryapp.entity.User;
import com.wileyedge.libraryapp.entity.UserBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void findUserServiceTest() {
        User aUser = new User();
        aUser.setfName("For Testing");
        aUser.setlName("fake authors");
        aUser.setEmail("fake description");
        userService.addNewUser(aUser);

        User returnUser = userService.getUserById(1);
        assertNotNull(returnUser);
        assertEquals("For Testing", returnUser.getfName());
    }

//    @Test
//    public void findAllUsersServiceTest() {
//        List<User> users = userService.get();
//        assertNotNull(books);
//        assertEquals(40, books.size());
//    }
//
    @Test
    public void userNotFoundServiceTest() {
        User notFound = userService.getUserById(99);
        assertNull(notFound);
    }

    @Test
    public void updateUserServiceTest() {
        User user = new User();
        user.setUid(1);
        user.setfName("Updated User fName");
        user.setlName("Updated User lName");
        user.setEmail("Updated User Email");

        User upUser = userService.updateUserInfo(user);
        assertNotNull(upUser);
        assertEquals((Integer)1, upUser.getUid());
        assertEquals("Updated User fName", upUser.getfName());
        assertEquals("Updated User lName", upUser.getlName());
        assertEquals("Updated User Email", upUser.getEmail());
    }

    @Test
    public void userNotUpdatedServiceTest() {
        User user = new User();
        user.setUid(1);
        user.setfName("Updated User fName");
        user.setlName("Updated User lName");
        user.setEmail("Updated User Email");

        User upUser = userService.updateUserInfo(user);
        assertNotNull(upUser);
        assertEquals("User NOT found. Could not update", upUser.getfName());
    }

//    @Test
//    public void userAddServiceTest() {
//        User user = new User();
//        user.setfName("New User fName");
//        user.setlName("New User lName");
//        user.setEmail("New User Email");
//        User newUser = userService.addNewUser(user);
//        assertNotNull(newBook);
//        assertEquals("New Book Title", newBook.getTitle());
//        assertEquals("New Book Authors", newBook.getAuthors());
//    }
//
//    @Test
//    public void bookNoAddServiceTest() {
//        Book book = new Book();
//        book.setTitle("");
//        Book newBook = bookService.addNewBook(book);
//        assertNotNull(newBook);
//        assertEquals("Book NOT added. Title empty.", newBook.getTitle());
//    }
//
//    @Test
//    public void bookDeleteServiceTest() {
//        Book book = new Book();
//        book.setTitle("");
//        Book newBook = bookService.addNewBook(book);
//        assertNotNull(newBook);
//        assertEquals("Book NOT added. Title empty.", newBook.getTitle());
//    }
}