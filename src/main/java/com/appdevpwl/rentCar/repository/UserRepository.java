package com.appdevpwl.rentCar.repository;

import com.appdevpwl.rentCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);
   
// select user with USER role
   @Query("SELECT u FROM User u left join u.roles a WHERE a.name ='ROLE_USER' ")
   List<User> findAllClients();



}
