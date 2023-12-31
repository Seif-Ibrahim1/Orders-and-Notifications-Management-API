package com.example.ordersmanagement.product;

import com.example.ordersmanagement.account.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService ProductServiceInterface;

    @GetMapping({"/products"})
    public Product[] getAll() {
        return this.ProductServiceInterface.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        System.out.println("in get with id:"+id);
        return this.ProductServiceInterface.getProduct(id);
    }

}
