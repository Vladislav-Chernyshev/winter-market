package ru.geekbrains.spring.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.wintermarket.entities.Product;
import ru.geekbrains.spring.wintermarket.services.CartService;
import ru.geekbrains.spring.wintermarket.services.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;



    @GetMapping("/add/{id}")
    public void addCartProduct(@PathVariable Long id) {
        cartService.addProductToCart(id);
    }

    @GetMapping
    public List<Product> findAllCartProducts() {
        return cartService.findAll();
    }


}
