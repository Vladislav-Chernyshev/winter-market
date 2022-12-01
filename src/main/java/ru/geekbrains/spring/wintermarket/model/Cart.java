package ru.geekbrains.spring.wintermarket.model;

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

    public void add(Product product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void clearCart() {
        items.clear();
        totalPrice = 0;
    }

    public void plusProductToCart(Long itemId) {
        for (CartItem item : items) {
            if (item.getProductId().equals(itemId)) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
                recalculate();
                return;
            }

        }
    }

    public void minusProductToCart(Long itemId) {
        for (CartItem item : items) {
            if (item.getProductId().equals(itemId)) {
                if (item.getQuantity() != 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    item.setPrice(item.getPricePerProduct() * item.getQuantity());
                    recalculate();
                }
                return;
            }

        }
    }

    public void deleteProductFromCart(Long itemId) {
        if (items.removeIf(item -> item.getProductId().equals(itemId))) {
            recalculate();
        }
    }


}
