package com.example.springboot.service;

import com.example.springboot.pojo.User;

public interface UserService {
    public User getUserByUsername(String username);

    public boolean isExist(String username);

    public void addUser(User user);

    public User LoginIn(String username, String password);

    public void updatePassword(String username, String password);

}
