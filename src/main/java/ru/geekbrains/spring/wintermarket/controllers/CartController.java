package ru.geekbrains.spring.wintermarket.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.wintermarket.dtos.Cart;
import ru.geekbrains.spring.wintermarket.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

    @GetMapping("/plus/{id}")
    public void plusProductToCart(@PathVariable Long id){
        cartService.plusProductToCart(id);
    }

    @GetMapping("/minus/{id}")
    public void minusProductToCart(@PathVariable Long id){
        cartService.minusProductToCart(id);
    }

}
