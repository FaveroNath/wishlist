package com.community.wishlist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.wishlist.model.Wishlist;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findByCustomerId(Long customerId);

}
