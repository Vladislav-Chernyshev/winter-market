package ru.geekbrains.spring.wintermarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.wintermarket.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;


    private final List<Product> cartProducts = new ArrayList<>();

    public List<Product> findAll(){
        return cartProducts;
    }

    public void addProductToCart(Long id){
      cartProducts.add(productService.findById(id).get());
    }


}
