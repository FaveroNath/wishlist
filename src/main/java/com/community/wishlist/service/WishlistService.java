package com.community.wishlist.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.wishlist.exception.ResourceNotFoundException;
import com.community.wishlist.model.Product;
import com.community.wishlist.model.Wishlist;
import com.community.wishlist.repository.WishListRepository;

@Service
public class WishlistService {

    @Autowired
    private WishListRepository wishListRepository;

    public Wishlist create(Wishlist wishlist) {
        return wishListRepository.save(wishlist);
    }

    public Optional<Wishlist> findByCustomerId(Long customerId) {
        return wishListRepository.findByCustomerId(customerId);
    }

    public Wishlist addProject(Set<Product> newProducts, Long id) throws ResourceNotFoundException {
        Wishlist wishlist = wishListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        wishlist.getProducts().addAll(newProducts);

        return wishlist;
    }

    public void delete(Long id) {
        Wishlist wishlist = wishListRepository.getOne(id);

        wishListRepository.delete(wishlist);
    }

}
