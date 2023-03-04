package com.community.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.wishlist.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
