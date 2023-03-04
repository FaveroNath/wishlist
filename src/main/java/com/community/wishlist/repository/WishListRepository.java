package com.community.wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.wishlist.model.Wishlist;

public interface WishListRepository extends JpaRepository<Wishlist, Long> {

}
