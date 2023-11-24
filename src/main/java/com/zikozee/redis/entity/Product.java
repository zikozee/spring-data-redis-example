package com.zikozee.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")  //also used in repo
public class Product implements Serializable {
    //objects saved to cache must be serializable

    @Id
    private String id;
    private String name;
    private int qty;
    private long price;
}
