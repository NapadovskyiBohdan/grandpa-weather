package com.grandpaweather.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Document
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class WeatherTriggerRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
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
    private String trigger_id;

    public static WeatherTriggerRelation buildFromResponse(WeatherData data) {
        WeatherTriggerRelation relation = new WeatherTriggerRelation();
        relation.setDate(data.getDate());
        relation.setCityId(data.getCity().getId());
        relation.setName(data.getCity().getName());
        relation.setTemp(data.getMain().getTemp());
        relation.setFeels_like(data.getMain().getFeels_like());
        relation.setTemp_min(data.getMain().getTemp_min());
        relation.setTemp_max(data.getMain().getTemp_max());
        relation.setPressure(data.getMain().getPressure());
        relation.setHumidity(data.getMain().getHumidity());
        relation.setClouds(data.getClouds().getAll());
        relation.setWeather_main(data.getWeather().get(0).getMain());
        relation.setWeather_description(data.getWeather().get(0).getDescription());
        relation.setVisibility(data.getVisibility());
        return  relation;


    }

    public void updateFromResponse(WeatherData data) {
        this.setDate(data.getDate());
        this.setCityId(data.getCity().getId());
        this.setName(data.getCity().getName());
        this.setTemp(data.getMain().getTemp());
        this.setFeels_like(data.getMain().getFeels_like());
        this.setTemp_min(data.getMain().getTemp_min());
        this.setTemp_max(data.getMain().getTemp_max());
        this.setPressure(data.getMain().getPressure());
        this.setHumidity(data.getMain().getHumidity());
        this.setClouds(data.getClouds().getAll());
        this.setWeather_main(data.getWeather().get(0).getMain());
        this.setWeather_description(data.getWeather().get(0).getDescription());
        this.setVisibility(data.getVisibility());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherTriggerRelation relation = (WeatherTriggerRelation) o;
        return getCityId() == relation.getCityId() && Double.compare(getTemp(), relation.getTemp()) == 0
                && Double.compare(getFeels_like(), relation.getFeels_like()) == 0 && Double.compare(getTemp_min(), relation.getTemp_min()) == 0
                && Double.compare(getTemp_max(), relation.getTemp_max()) == 0
                && getPressure() == relation.getPressure() && getHumidity() == relation.getHumidity()
                && getClouds() == relation.getClouds() && getVisibility() == relation.getVisibility()
                && Objects.equals(getId(), relation.getId()) && Objects.equals(getDate(), relation.getDate())
                && Objects.equals(getName(), relation.getName()) && Objects.equals(getWeather_main(), relation.getWeather_main())
                && Objects.equals(getWeather_description(), relation.getWeather_description()) && Objects.equals(getTrigger_id(), relation.getTrigger_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getCityId(), getName(), getTemp(), getFeels_like(), getTemp_min(), getTemp_max(), getPressure(), getHumidity(), getClouds(), getWeather_main(), getWeather_description(), getVisibility(), getTrigger_id());
    }
}
