package com.grandpaweather.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ForecastResponse extends AbstractWeatherResponse {

    private String message;
    private int cnt;
    private List<WeatherResponse> list;
    private City city;

}
