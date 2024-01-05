package com.grandpaweather.domain.dto;

import com.grandpaweather.domain.Trigger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class TriggerDTO  {

    private String id;
    private String name;
    private String description;
    private List<MediatorDTO> mediators;

    public static TriggerDTO buildDtoFromEntity(Trigger trigger, List<MediatorDTO> mediators) {
        return TriggerDTO.builder()
                .id(trigger.getId())
                .name(trigger.getName())
                .description(trigger.getDescription())
                .mediators(mediators)
                .build();
    }

}
