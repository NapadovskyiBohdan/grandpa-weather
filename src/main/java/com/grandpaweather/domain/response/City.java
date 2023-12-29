package com.grandpaweather.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private long id;
    private String name;
    private Coord coord;
    private String country;
    private long population;
    private int timezone;
    private long sunrise;
    private long sunset;



}
