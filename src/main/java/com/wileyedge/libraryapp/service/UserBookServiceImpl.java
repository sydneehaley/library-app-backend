package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.dao.UserBookDao;
import com.wileyedge.libraryapp.entity.Book;
import com.wileyedge.libraryapp.entity.User;
import com.wileyedge.libraryapp.entity.UserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBookServiceImpl implements UserBookService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserBookDao userBookDao;

    @Autowired
    private BookServiceImpl bookService;
    @Override
    public void addBookToUser(Integer bid, Integer uid) {
        User user = userService.getUserById(uid);
        Book book = bookService.getBookById(bid);
        UserBook userBook = userBookDao.findByUserAndBook(user, book);

        if (user!=null && book!=null && userBook==null) {
            userBook = new UserBook(user, book);
            userBookDao.save(userBook);
        }
    }

    @Override
    public void removeBookFromUser(Integer bid, Integer uid) {
        User user = userService.getUserById(uid);
        Book book = bookService.getBookById(bid);

        if (user!=null && book!=null) {
            UserBook userBook = userBookDao.findByUserAndBook(user, book);
            userBookDao.deleteById(userBook.getBorrowId());
        }
    }

    @Override
    public List<Book> getAllByUid(Integer uid) {
        User user = userService.getUserById(uid);
        if (user == null) {
            return null;
        }
        return user.getBooks().stream().map(UserBook::getBook).collect(Collectors.toList());
    }
}
