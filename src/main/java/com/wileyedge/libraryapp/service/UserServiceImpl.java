package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.dao.UserDao;
import com.wileyedge.libraryapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    BookServiceImpl bookService;


    public UserServiceImpl(UserDao userDao, BookServiceImpl bookService) {
        this.userDao = userDao;
        this.bookService = bookService;
    }

    @Override
    public User addNewUser(User user) {
        try {
            user.setMaxBorrowCount(5);
            userDao.save(user);
        } catch (Exception ex) {
            user.setfName("User NOT added. Error: " + ex.getMessage());
        }
        return user;
    }

    @Override
    public User getUserById(Integer uid) {
        return userDao.findById(uid).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User updateUserInfo(User user) {
        User getUser = getUserById(user.getUid());
        if (getUser==null) {
            user.setfName("User NOT found. Could not update");
        } else {
            userDao.save(user);
        }
        return user;
    }

    @Override
    public User matchUser(User user) {
        User userDB = this.getUserByEmail(user.getEmail());
        if (userDB == null) {
            user.setfName("User not found");
        } else if (user.getPassword() == null || !userDB.getPassword().equals(user.getPassword())) {
            user.setfName("Wrong password");
        } else {
            user = userDB;
        }
        return user;
    }
}
