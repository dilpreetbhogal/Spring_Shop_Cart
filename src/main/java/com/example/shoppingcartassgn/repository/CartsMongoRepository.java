package com.example.shoppingcartassgn.repository;

import com.example.shoppingcartassgn.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartsMongoRepository extends MongoRepository<Cart,Integer> {
}
