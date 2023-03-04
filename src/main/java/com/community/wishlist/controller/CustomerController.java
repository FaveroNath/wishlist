package com.community.wishlist.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.community.wishlist.exception.EntityAlreadyExistsException;
import com.community.wishlist.exception.ResourceNotFoundException;
import com.community.wishlist.model.Customer;
import com.community.wishlist.service.CustomerService;

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ArrayList<Customer> getAll() {
        return customerService.getAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Customer> create(
            @RequestBody @Validated Customer newCustomer,
            UriComponentsBuilder uriBuilder) throws EntityAlreadyExistsException {
        Customer customer = customerService.create(newCustomer);
        URI uri = uriBuilder.path("/customer/{id}").buildAndExpand(newCustomer.getId()).toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> read(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Customer> update(
            @PathVariable Long id,
            @RequestBody @Validated Customer newCustomer) throws ResourceNotFoundException {
        Customer customer = customerService.update(newCustomer, id);
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}