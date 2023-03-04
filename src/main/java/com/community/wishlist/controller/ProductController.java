package com.community.wishlist.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

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
import com.community.wishlist.service.ProductService;

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ArrayList<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Product> create(@RequestBody @Validated Product newProduct, UriComponentsBuilder uriBuilder) {
        Product product = productService.create(newProduct);
        URI uri = uriBuilder.path("/product/{id}").buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> read(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Validated Product newProduct)
            throws ResourceNotFoundException {
        Product product = productService.update(newProduct, id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
