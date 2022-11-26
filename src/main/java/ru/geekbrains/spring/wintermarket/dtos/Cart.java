package ru.geekbrains.spring.wintermarket.dtos;

import lombok.Data;
import ru.geekbrains.spring.wintermarket.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items;
    private int totalPrice;


    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) { //TODO: Доработать в ДЗ
        boolean check = false;
        for (CartItem cartItem : items) {
            if (cartItem.getProductId() == product.getId()) {
                check = true;
                break;
            }
        }
        if (check) {
            for (CartItem item : items) {
                if (item.getProductId() != null && item.getProductId() == product.getId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    item.setPrice(item.getPricePerProduct() * item.getQuantity());
                    break;
                }
            }
        } else {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
            System.out.println("В корзину добавлен новый товар");
        }

        if (items.isEmpty()) {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        }
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void addToCart(Product product) {

    }


}
