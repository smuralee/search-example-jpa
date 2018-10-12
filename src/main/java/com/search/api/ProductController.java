package com.search.api;

import com.search.persistence.entities.Product;
import com.search.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return this.repository.findAll();
    }
}
