package com.example.springboot.service;

import com.example.springboot.dao.UserDao;
import com.example.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    // UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean isExist(String username) {
        User user = getUserByUsername(username);
        return user != null;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
        // userRepository.save(user);
    }

    @Override
    public User LoginIn(String username, String password) {
        return userDao.getInfo(username, password);
    }

    @Override
    public void updatePassword(String username, String password) {
        userDao.updatePassword(username, password);
    }
}
