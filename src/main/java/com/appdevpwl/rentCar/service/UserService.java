package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.exception.EmailExistException;
import com.appdevpwl.rentCar.model.User;

import java.util.List;

public interface UserService {
    List<User> allUser();

    User simpleUser(Long id);

    User findUserByEmail(String email);

    void save(User user) throws EmailExistException;
}
