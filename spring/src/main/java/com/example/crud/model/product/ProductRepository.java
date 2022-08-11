package com.example.crud.model.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Optional<Product> searchProductByBarCode(@Param(value = "barCode") String barCode);
}
