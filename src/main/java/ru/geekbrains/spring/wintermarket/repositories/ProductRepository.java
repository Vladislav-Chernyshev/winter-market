package ru.geekbrains.spring.wintermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.wintermarket.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
