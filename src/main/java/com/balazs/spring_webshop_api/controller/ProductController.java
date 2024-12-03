package com.balazs.spring_webshop_api.controller;

import com.balazs.spring_webshop_api.model.Product;
import com.balazs.spring_webshop_api.model.ProductException;
import com.balazs.spring_webshop_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    public Optional<Product> getProduct(@PathVariable int id){
        return Optional.ofNullable(productService.findOne(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PRODUCT NOT FOOOOUND")));
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product savedProduct = null;
        try {
            savedProduct = productService.addProduct(product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
    }

    @GetMapping("products/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.findOne(productId).get();
        System.out.println("products/id/image  method  called <-------------");
        System.out.println(product);
        System.out.println(Arrays.toString(product.getImageData()));
        return  new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
    }
}
