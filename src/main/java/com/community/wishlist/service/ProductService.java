package com.community.wishlist.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.community.wishlist.exception.ResourceNotFoundException;
import com.community.wishlist.model.Product;
import com.community.wishlist.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public ArrayList<Product> getAll() {
        return new ArrayList<>(productRepository.findAll());
    }

    public Product update(Product newProduct, Long id) throws ResourceNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new ResourceNotFoundException(""));

        product.setTitle(newProduct.getTitle());
        product.setPrice(newProduct.getPrice());

        return product;
    }

    @Transactional
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new ResourceNotFoundException(""));

        productRepository.delete(product);
    }

}
