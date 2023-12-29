package com.grandpaweather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grandpaweather.domain.response.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class MainWeatherData {

    private LocalDateTime dt;
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
