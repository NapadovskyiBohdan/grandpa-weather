package com.grandpaweather.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "triggers")
public class Trigger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private String description;

    private List<String> mediators_id;

    public static Trigger buildByNameAndDescription(String name, String description, List<String> mediators_id) {
        return Trigger.builder()
                .name(name)
                .description(description)
                .mediators_id(mediators_id)
                .build();
    }
}
