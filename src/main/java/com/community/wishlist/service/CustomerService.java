package com.community.wishlist.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.wishlist.exception.EntityAlreadyExistsException;
import com.community.wishlist.exception.ResourceNotFoundException;
import com.community.wishlist.model.Customer;
import com.community.wishlist.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(Customer customer) throws EntityAlreadyExistsException {
        customerRepository.findByEmail(customer.getEmail()).orElseThrow(() -> new EntityAlreadyExistsException(""));

        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public ArrayList<Customer> getAll() {
        return new ArrayList<>(customerRepository.findAll());
    }

    public Customer update(Customer newCustomer, Long id) throws ResourceNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        Customer customer = optionalCustomer.orElseThrow( () -> new ResourceNotFoundException(""));

        customer.setEmail(newCustomer.getEmail());
        customer.setName(newCustomer.getName());

        return customer;
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.orElseThrow(() -> new ResourceNotFoundException(""));
        customerRepository.delete(customer);
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

}
