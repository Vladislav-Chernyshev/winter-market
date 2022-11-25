package ru.geekbrains.spring.wintermarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor

public class Cart {


    private Long id;

    private String title;

    private  int price;
}
