package com.community.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.wishlist.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
