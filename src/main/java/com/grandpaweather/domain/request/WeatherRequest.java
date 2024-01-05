package com.grandpaweather.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {

    private double lat = 50.4500336;
    private double lon = 30.5241361;
    private String units = "metric";
    private String countryCode;
    private String city;
    private String zipCode;
    private String lang;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherRequest request = (WeatherRequest) o;
        return Double.compare(getLat(), request.getLat()) == 0 && Double.compare(getLon(), request.getLon()) == 0 && Objects.equals(getUnits(), request.getUnits()) && Objects.equals(getCountryCode(), request.getCountryCode()) && Objects.equals(getCity(), request.getCity()) && Objects.equals(getZipCode(), request.getZipCode()) && Objects.equals(getLang(), request.getLang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLon(), getUnits(), getCountryCode(), getCity(), getZipCode(), getLang());
    }

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
