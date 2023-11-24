package com.zikozee.redis.repository;

import com.zikozee.redis.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductDao2 {

    private final ProductRepository productRepository;

    public Product save(Product product){
        product.setId(UUID.randomUUID().toString().replace("-", ""));
        return productRepository.save(product);
    }

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
         productRepository.findAll().forEach(products::add);
         return products;
    }

    public Product findProductById(String id){
        return productRepository.findById(id).orElse(null);
    }

    public String deleteProduct(String id){
        productRepository.deleteById(id);
        return "Product with id: " + id + " removed";
    }
}
