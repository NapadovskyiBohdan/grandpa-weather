package com.grandpaweather.repository;

import com.grandpaweather.domain.Mediator;
import com.grandpaweather.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> getProductsByIdIn(List<String> ids);
    List<Product> getProductByNameIn(List<String> productsName);
}
