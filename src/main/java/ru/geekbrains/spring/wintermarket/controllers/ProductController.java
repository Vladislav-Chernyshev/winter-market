package ru.geekbrains.spring.wintermarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.wintermarket.dtos.ProductDto;
import ru.geekbrains.spring.wintermarket.entities.Product;
import ru.geekbrains.spring.wintermarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.wintermarket.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream()
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findProductById(@PathVariable Long id) {
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent()) {
//            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Продукт не найден, id: " + id),
//                    HttpStatus.NOT_FOUND);
//            return err;
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());

    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }


}
