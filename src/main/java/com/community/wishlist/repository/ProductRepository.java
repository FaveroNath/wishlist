package com.community.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.wishlist.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
