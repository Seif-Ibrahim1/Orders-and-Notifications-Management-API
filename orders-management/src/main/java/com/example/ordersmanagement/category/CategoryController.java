package com.example.ordersmanagement.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryService CategoryServiceInterface;

    @GetMapping({"/categories"})
    public Category[] getAll() {
        return this.CategoryServiceInterface.getAllCategory();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable("id") int id) {
        System.out.println("in get with id:"+id);
        return this.CategoryServiceInterface.getCategory(id);
    }
}
