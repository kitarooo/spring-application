package com.example.springapplication.service;

import com.example.springapplication.entity.Product;
import com.example.springapplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product update(Long id, Product updatedProduct) {
        Product existingProduct = findById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        return productRepository.save(existingProduct);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
