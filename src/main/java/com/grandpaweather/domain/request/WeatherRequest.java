package com.grandpaweather.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {

    private double lat;
    private double lon;
    private String units;
    private String countryCode;
    private String city;
    private String zipCode;
    private String lang;


    @Override
    public String toString() {
        return "WeatherRequest{" +
                ", lat=" + lat +
                ", lon=" + lon +
                ", units='" + units + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
