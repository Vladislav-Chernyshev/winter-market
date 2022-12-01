package ru.geekbrains.spring.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.wintermarket.entities.User;
import ru.geekbrains.spring.wintermarket.services.OrderService;
import ru.geekbrains.spring.wintermarket.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal/*, @RequestBody OrderData orderData*/) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException());
        orderService.createOrder(user);
    }
}
