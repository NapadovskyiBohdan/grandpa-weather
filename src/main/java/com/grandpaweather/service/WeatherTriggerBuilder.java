package com.grandpaweather.service;


import com.grandpaweather.domain.Trigger;
import com.grandpaweather.domain.WeatherTriggerRelation;

public interface WeatherTriggerBuilder {

    Trigger buildTriggerByWeatherData(WeatherTriggerRelation currentDay, WeatherTriggerRelation nextDay);

    Trigger getTriggerByName(String name);

}
