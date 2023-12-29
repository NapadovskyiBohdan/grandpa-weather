package com.grandpaweather.domain.dto;

import com.grandpaweather.domain.Trigger;
import com.grandpaweather.domain.WeatherTriggerRelation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WeatherTriggerDTO {

    private LocalDateTime date;
    private long cityId;
    private String name;
    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private int pressure;
    private int humidity;
    private int clouds;
    private String weather_main;
    private String weather_description;
    private int visibility;
    private TriggerDTO trigger;


    public static WeatherTriggerDTO buildDTOFromEntity(WeatherTriggerRelation relation, TriggerDTO trigger) {
        return WeatherTriggerDTO.builder()
                .date(relation.getDate())
                .cityId(relation.getCityId())
                .name(relation.getName())
                .temp(relation.getTemp())
                .feels_like(relation.getFeels_like())
                .temp_min(relation.getTemp_min())
                .temp_max(relation.getTemp_max())
                .pressure(relation.getPressure())
                .humidity(relation.getHumidity())
                .clouds(relation.getClouds())
                .weather_main(relation.getWeather_main())
                .weather_description(relation.getWeather_description())
                .visibility(relation.getVisibility())
                .trigger(trigger)
                .build();
    }


}
