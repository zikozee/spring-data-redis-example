package com.zikozee.redis.repository;

import com.zikozee.redis.entity.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    public static final String HASH_KEY = "Product";

    private final RedisTemplate template;

    public Product save(Product product){
        product.setId(UUID.randomUUID().toString().replace("-",""));
        //hash, id, value
        template.opsForValue().set(product.getId(), product);

        return product;
    }

    public List<Product> findAll(){
        return Collections.emptyList();
    }

    public Product findProductById(String id){
        return (Product)template.opsForValue().get(id);
    }

    public String deleteProduct(String id){
        template.opsForHash().delete(HASH_KEY, id);
        return "Product with id: " + id + " removed";
    }
}
