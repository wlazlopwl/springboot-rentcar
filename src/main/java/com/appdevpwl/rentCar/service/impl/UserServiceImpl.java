package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.exception.EmailExistException;
import com.appdevpwl.rentCar.model.User;
import com.appdevpwl.rentCar.repository.UserRepository;
import com.appdevpwl.rentCar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUser() {
        return userRepository.findAllClients();
    }

    public User simpleUser(Long id){
        return userRepository.getOne(id);
    }
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void save(User user) throws EmailExistException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }


}
