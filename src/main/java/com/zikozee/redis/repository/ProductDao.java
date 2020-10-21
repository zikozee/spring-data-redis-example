package com.zikozee.redis.repository;

import com.zikozee.redis.entity.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    public static final String HASH_KEY = "Product";

    private final RedisTemplate template;

    public Product save(Product product){
        //hash, id, value
        template.opsForHash().put(HASH_KEY, product.getId(), product);

        return product;
    }

    public List<Product> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int id){
        return (Product)template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(int id){
        template.opsForHash().delete(HASH_KEY, id);
        return "Product with id: " + id + " removed";
    }
}
