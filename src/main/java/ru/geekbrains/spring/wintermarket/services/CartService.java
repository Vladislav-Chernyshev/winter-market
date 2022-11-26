package ru.geekbrains.spring.wintermarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.wintermarket.dtos.Cart;
import ru.geekbrains.spring.wintermarket.dtos.CartItem;
import ru.geekbrains.spring.wintermarket.entities.Product;
import ru.geekbrains.spring.wintermarket.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId).orElseThrow(()
                -> new ResourceNotFoundException("Не удается добавить продукт с id: " + productId + " в корзину. Продукт не найден."));
        tempCart.add(product);
    }

    public void clearCart() {
        tempCart.clearCart();
    }

    public void plusProductToCart(Long itemId) {
        tempCart.plusProductToCart(itemId);
    }

    public void minusProductToCart(Long itemId) {
        tempCart.minusProductToCart(itemId);
    }

}
