package com.zikozee.redis.contoller;

import com.zikozee.redis.entity.Product;
import com.zikozee.redis.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    @PostMapping
    public Product save(@RequestBody Product product){
        return productDao.save(product);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productDao.findAll();
    }

    @Cacheable(cacheNames = {"product1"}, key = "#id")
    @GetMapping("{id}")
    public Product findProduct(@PathVariable("id") String  id){
        return productDao.findProductById(id);
    }

    @DeleteMapping("{id}")
    public String removeProduct(@PathVariable("id") String  id){
        return productDao.deleteProduct(id);
    }
}
