package com.grandpaweather.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleWeatherResponse extends WeatherResponse {

    private String name;
    private Coord coord;
    private String base;
    private int timezone;
    private long id;


}
