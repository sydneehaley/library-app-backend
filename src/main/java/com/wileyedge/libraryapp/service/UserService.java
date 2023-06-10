package com.wileyedge.libraryapp.service;

import com.wileyedge.libraryapp.entity.User;

import java.util.List;

public interface UserService {
    User addNewUser(User user);
    User getUserById(Integer uid);
    User getUserByEmail(String email);
    User updateUserInfo(User user);
    User matchUser(User user);
}
