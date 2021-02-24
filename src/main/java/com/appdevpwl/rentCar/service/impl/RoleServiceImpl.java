package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.model.Role;
import com.appdevpwl.rentCar.repository.RoleRepository;
import com.appdevpwl.rentCar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> allRole() {

        return roleRepository.findAll();
    }

    public List<Role> userRoleByName(String role) {
        return roleRepository.findRoleByName(role);
    }
}
