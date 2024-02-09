package com.zikozee.redis.repository;

import com.zikozee.redis.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductDao2Test {

    @Autowired
    private ProductRepository repository;


    @Order(1)
    @Test
    void save() {



        Product productA = new Product();

        productA.setName("AAAA");
        productA.setQty(4);
        productA.setPrice(450);


        Product productB = new Product();

        productB.setName("BBBB");
        productB.setQty(2);
        productB.setPrice(450);


        Product productC = new Product();

        productC.setName("CCCC");
        productC.setQty(2);
        productC.setPrice(450);


        Product productD = new Product();

        productD.setName("DDDD");
        productD.setQty(2);
        productD.setPrice(450);


        Product productE = new Product();

        productE.setName("EEEE");
        productE.setQty(2);
        productE.setPrice(450);


        List<Product> productList = List.of(productA, productB, productC, productD, productE);

        Iterable<Product> products = repository.saveAll(productList);

        Assertions.assertThat(products.iterator().hasNext()).isTrue();
    }

    @Order(2)
    @Test
    void findAll() {
        List<Product>  products = new ArrayList<>();
        Iterable<Product> all = repository.findAll();

        all.forEach(products::add);
        System.out.println(products);
        Assertions.assertThat(products).hasSize(5);
    }


    @Order(3)
    @Test
    void deleteProduct() {
        Assertions.assertThat(repository.findAll().iterator().hasNext()).isTrue();
        repository.deleteAll();
    }
}