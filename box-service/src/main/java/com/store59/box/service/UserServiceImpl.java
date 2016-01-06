package com.store59.box.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store59.box.model.User;
import com.store59.box.remoting.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUsers(User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

}
