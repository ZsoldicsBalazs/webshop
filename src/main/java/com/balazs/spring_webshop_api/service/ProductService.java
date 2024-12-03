package com.balazs.spring_webshop_api.service;

import com.balazs.spring_webshop_api.model.Product;
import com.balazs.spring_webshop_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findOne(int id) {
        return productRepository.findById(id);
    }
}
