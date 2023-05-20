package com.example.shoppingcartassgn.repository;

import com.example.shoppingcartassgn.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemsMongoRepository  extends MongoRepository<Item,Integer> {
}
