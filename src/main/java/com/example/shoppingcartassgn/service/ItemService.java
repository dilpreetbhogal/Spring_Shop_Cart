package com.example.shoppingcartassgn.service;

import com.example.shoppingcartassgn.model.Cart;
import com.example.shoppingcartassgn.model.Item;
import com.example.shoppingcartassgn.repository.CartsMongoRepository;
import com.example.shoppingcartassgn.repository.ItemsMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemService {

    @Autowired
    ItemsMongoRepository itemsMongoRepository;
    @Autowired
    CartsMongoRepository cartsMongoRepository;




    public List<Item> getItems() {
        return this.itemsMongoRepository.findAll();
    }
    public List<Cart> getCartItems(){
        return  this.cartsMongoRepository.findAll();
    }


    public void addingInventory(Item item) {
        this.itemsMongoRepository.save(item);

    }




    public void addCartItem(int id) {

        if (this.cartsMongoRepository.existsById(id)) {
            Cart cart = this.cartsMongoRepository.findById(id).get();
            cart.setQuantity(cart.getQuantity() + 1);
            this.cartsMongoRepository.save(cart);
        } else {

            Item temp = this.itemsMongoRepository.findById(id).get();
            this.cartsMongoRepository.save(
                    new Cart(temp.getId(),
                            temp.getName(),
                            temp.getPrice(),
                            1));
        }

    }

    public ResponseEntity<Cart> deleteCartItem(int id) {

        Optional<Cart> optionalCart = this.cartsMongoRepository.findById(id);

        if (optionalCart.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cart cart = optionalCart.get();
        if (cart.getQuantity() == 1) {
            this.cartsMongoRepository.deleteById(id);

        }
        else if (cart.getQuantity() > 1) {
            cart.setQuantity(cart. getQuantity() - 1);
            this.cartsMongoRepository.save(cart);
        }
        return ResponseEntity.created(null).body(cart);

    }




    public int totalPrice(){
        int sum=0;
        for(Cart temp : this.cartsMongoRepository.findAll()){
            sum=sum+temp.getPrice()*temp.getQuantity();
        }
        return sum;
    }

}
