package com.example.ordersmanagement.product;

import java.util.Set;


import org.springframework.stereotype.Service;
@Service
public class ProductService implements ProductServiceInterface {


    public Product[] getAllProducts() {
        try {
            Set<Integer> ids = ProductRepositry.products.keySet();
            Product[] p = new Product[ids.size()];
            int i=0;
            for(Integer id : ids){
                p[i] = ProductRepositry.products.get(id);
                i++;
            }
            return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllProducts as" + e.getMessage());
        }
        return null;
    }


    public Product getProduct(int id) {
        try {
            return ProductRepositry.products.get(id);
        } catch (Exception e) {
            System.out.println("Exception in get product as" + e.getMessage());
        }
        return null;
    }
}
