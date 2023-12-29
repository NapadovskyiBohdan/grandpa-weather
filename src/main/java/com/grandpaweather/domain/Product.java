package com.grandpaweather.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product implements Serializable {

    @Id
    private String id;
    private String category;
    private String name;
    private String imageURI;
    private double price;

    public static Product buildByNameAndDescription(String category, String name) {
        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        return product;
    }
}
