package com.grandpaweather.service;


import com.grandpaweather.domain.dto.WeatherTriggerDTO;
import com.grandpaweather.domain.request.WeatherRequest;
import com.grandpaweather.domain.response.ForecastResponse;

import java.util.List;

public interface WeatherService {

    List<WeatherTriggerDTO> getWeatherForCurrentDayByRequest(WeatherRequest request);

    void updateWeatherForCurrentDayByRequest(WeatherRequest request);

    ForecastResponse getForecastResponseByRequest(WeatherRequest request);
}
