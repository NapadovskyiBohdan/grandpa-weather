package com.grandpaweather.domain.dto;

import com.grandpaweather.domain.response.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private long id;
    private String name;
    private double lon;
    private double lat;
    private String country;


    public static CityDTO buildFromEntity(City city) {
        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setLon(city.getCoord().getLon());
        dto.setLat(city.getCoord().getLat());
        dto.setCountry(city.getCountry());
        return dto;
    }
}
