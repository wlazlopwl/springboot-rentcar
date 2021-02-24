package com.appdevpwl.rentCar.service;

import com.appdevpwl.rentCar.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> allCustomers();

    Customer getCustomerById(Long id);
}
