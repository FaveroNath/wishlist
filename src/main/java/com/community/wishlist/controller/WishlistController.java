package com.community.wishlist.controller;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.community.wishlist.exception.ResourceNotFoundException;
import com.community.wishlist.model.Product;
import com.community.wishlist.model.Wishlist;
import com.community.wishlist.service.WishlistService;

public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping
    @Transactional
    public ResponseEntity<Wishlist> create(@RequestBody @Validated Wishlist newWishlist,
            UriComponentsBuilder uriBuilder) {
        Wishlist wishlist = wishlistService.create(newWishlist);
        URI uri = uriBuilder.path("/wishlist/{id}").buildAndExpand(newWishlist.getId()).toUri();
        return ResponseEntity.created(uri).body(wishlist);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Wishlist> read(@PathVariable Long customerId) {
        Optional<Wishlist> wishlist = wishlistService.findByCustomerId(customerId);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Wishlist> addProduct(
            @PathVariable Long id,
            @RequestBody @Validated Set<Product> newProducts) throws ResourceNotFoundException {
        Wishlist wishlist = wishlistService.addProject(newProducts, id);

        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        wishlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
