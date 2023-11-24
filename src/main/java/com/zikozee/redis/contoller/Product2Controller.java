package com.zikozee.redis.contoller;

import com.zikozee.redis.entity.Product;
import com.zikozee.redis.repository.ProductDao;
import com.zikozee.redis.repository.ProductDao2;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product2")
@RequiredArgsConstructor
public class Product2Controller {

    private final ProductDao2 productDao;

    @PostMapping
    public Product save(@RequestBody Product product){
        return productDao.save(product);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productDao.findAll();
    }

    @Cacheable(cacheNames = {"product2"}, key = "#id")
    @GetMapping("{id}")
    public Product findProduct(@PathVariable("id") String  id){
        return productDao.findProductById(id);
    }

    @DeleteMapping("{id}")
    public String removeProduct(@PathVariable("id") String  id){
        return productDao.deleteProduct(id);
    }
}
