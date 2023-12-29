package com.grandpaweather.service;


import com.grandpaweather.domain.WeatherData;
import com.grandpaweather.domain.WeatherTriggerRelation;
import com.grandpaweather.domain.dto.WeatherTriggerDTO;

import java.util.List;

public interface WeatherTriggerManager {


    List<WeatherTriggerRelation> updateTriggersFromResponse(List<WeatherData> response);

    WeatherTriggerDTO buildWeatherTriggerDTO(WeatherTriggerRelation relation);
}
