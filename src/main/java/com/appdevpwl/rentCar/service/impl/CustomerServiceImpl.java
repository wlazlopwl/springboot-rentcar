package com.appdevpwl.rentCar.service.impl;

import com.appdevpwl.rentCar.model.Customer;
import com.appdevpwl.rentCar.repository.CustomerRepository;
import com.appdevpwl.rentCar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.getOne(id);
    }
}
