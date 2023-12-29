package com.grandpaweather.domain.dto;


import com.grandpaweather.domain.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;
    private String category;
    private String name;
    private String imageURI;
    private double price;



    public static ProductDTO buildDTOFromEntity(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId()) ;
        dto.setCategory(product.getCategory());
        dto.setName(product.getName());
        dto.setImageURI(product.getImageURI());
        dto.setPrice(product.getPrice());
        return dto;
    }

}
