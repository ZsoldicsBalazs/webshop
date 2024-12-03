package com.balazs.spring_webshop_api.controller;

import com.balazs.spring_webshop_api.model.Product;
import com.balazs.spring_webshop_api.model.ProductException;
import com.balazs.spring_webshop_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.findAll();
    }
    @GetMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable int id){
        return Optional.ofNullable(productService.findOne(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PRODUCT NOT FOOOOUND")));
    }
}
