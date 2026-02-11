package com.esell.product_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.esell.product_service.model.Product;
import com.esell.product_service.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(Product product) {
        if (product.getPrice().signum() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero!");
        }
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
