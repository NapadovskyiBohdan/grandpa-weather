package com.grandpaweather.domain;

import com.grandpaweather.domain.response.City;
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
    private City city;
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
        relation.setCity(data.getCity());
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
        this.setCity(data.getCity());
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
        WeatherTriggerRelation that = (WeatherTriggerRelation) o;
        return getPressure() == that.getPressure() && getHumidity() == that.getHumidity() && getClouds() == that.getClouds() && getVisibility() == that.getVisibility() && Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDate(), that.getDate()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getTemp(), that.getTemp()) && Objects.equals(getFeels_like(), that.getFeels_like()) &&
                Objects.equals(getTemp_min(), that.getTemp_min()) && Objects.equals(getTemp_max(), that.getTemp_max()) && Objects.equals(getWeather_main(), that.getWeather_main()) &&
                Objects.equals(getWeather_description(), that.getWeather_description()) && Objects.equals(getTrigger_id(), that.getTrigger_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getCity(), getTemp(), getFeels_like(), getTemp_min(), getTemp_max(),
                getPressure(), getHumidity(), getClouds(), getWeather_main(), getWeather_description(), getVisibility(), getTrigger_id());
    }
}
