package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.model.Role;

import java.util.List;

public interface RoleService {

     List<Role> allRole();

     List<Role> userRoleByName(String role);

}