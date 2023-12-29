package com.grandpaweather.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mediators")
public class Mediator implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String mediator;
    private String group;
    private List<String> products_id;


    public static Mediator createMediatorByNameAndProducts(String mediatorName, String groupName, List<String> products_id) {
        Mediator mediator = new Mediator();
        mediator.setMediator(mediatorName);
        mediator.setGroup(groupName);
        mediator.setProducts_id(products_id);
        return mediator;
    }
}
