package com.example.shoppingcartassgn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="shoppingcart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {


    @Id
    public int id;
    public String name;
    public int price;
    public int quantity;

}
