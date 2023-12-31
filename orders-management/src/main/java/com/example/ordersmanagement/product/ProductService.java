package com.example.ordersmanagement.product;

import java.util.Set;


import org.springframework.stereotype.Service;
@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepositry productRepositry;

    public ProductService(ProductRepositry productRepositry) {
        this.productRepositry = productRepositry;
    }

    public Product[] getAllProducts() {
        try {
            Set<Integer> ids = productRepositry.getProductIds();
            Product[] p = new Product[ids.size()];
            int i=0;
            for(Integer id : ids){
                p[i] = productRepositry.getProduct(id);
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
            return productRepositry.getProduct(id);
        } catch (Exception e) {
            System.out.println("Exception in get product as" + e.getMessage());
        }
        return null;
    }
}
