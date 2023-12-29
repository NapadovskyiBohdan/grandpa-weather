package com.grandpaweather.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse extends AbstractWeatherResponse {
    private long dt;
    private MainResponse main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private double pop;
    @JsonProperty("rain")
    private Rain rain;
    private SystemResponse sys;
    private String dt_txt;

}
