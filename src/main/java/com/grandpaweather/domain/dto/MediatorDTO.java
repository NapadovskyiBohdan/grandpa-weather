package com.grandpaweather.domain.dto;

import com.grandpaweather.domain.Mediator;
import com.grandpaweather.domain.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediatorDTO {

    private String id;
    private String mediator;
    private String group;
    private List<ProductDTO> products;



    public static MediatorDTO buildDTOFromEntity(Mediator mediator, List<ProductDTO> products) {
        MediatorDTO dto = new MediatorDTO();
        dto.setId(mediator.getId());
        dto.setMediator(mediator.getMediator());
        dto.setGroup(mediator.getGroup());
        dto.setProducts(products);
        return dto;
    }

}
