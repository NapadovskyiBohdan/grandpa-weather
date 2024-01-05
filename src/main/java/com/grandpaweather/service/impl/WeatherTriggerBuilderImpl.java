package com.grandpaweather.service.impl;

import com.grandpaweather.domain.Trigger;
import com.grandpaweather.domain.WeatherTriggerRelation;
import com.grandpaweather.error.TriggerNotFoundException;
import com.grandpaweather.repository.TriggerRepository;
import com.grandpaweather.service.WeatherTriggerBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherTriggerBuilderImpl implements WeatherTriggerBuilder {

    private static final Logger log = LoggerFactory.getLogger(WeatherTriggerBuilderImpl.class);
    private final TriggerRepository triggerRepository;

    @Override
    public Trigger buildTriggerByWeatherData(WeatherTriggerRelation currentDay, WeatherTriggerRelation nextDay) {
        String triggerName = calculateTriggerNameByWeatherType(currentDay, nextDay);
        return findTriggerByTriggerName(triggerName);
    }

    @Override
    public Trigger getTriggerByName(String name) {
        return findTriggerByTriggerName(name);
    }

    private String calculateTriggerNameByWeatherType(WeatherTriggerRelation currentDay, WeatherTriggerRelation nextDay) {
        String triggerName = getTriggerNameByTemperatureChanged(currentDay, nextDay);
        if (triggerName.equals("default")) {
            triggerName = getTriggerByWeatherChanging(currentDay, nextDay);
        }
        return triggerName;
    }

    private String getTriggerByWeatherChanging(WeatherTriggerRelation currentDay, WeatherTriggerRelation nextDay) {
        String result = nextDay.getWeather_main();
        if (currentDay.getWeather_main().equals("Clear") && (nextDay.getWeather_main().equals("Rain")
                || (nextDay.getWeather_main().equals("Thunderstorm")))) {
            result = "suddenRain";
        }
        return result;

    }

    private String getTriggerNameByTemperatureChanged(WeatherTriggerRelation currentDay, WeatherTriggerRelation nextDay) {
        log.info("Building trigger by weather data from first date {} and second date {}", currentDay.getDate(), nextDay.getDate());
        long sub = getTemperatureSubtract(currentDay, nextDay);
        String triggerName = "default";
        if ((nextDay.getTemp() > currentDay.getTemp()) && Math.abs(sub) >= 5)
            triggerName = "suddenWarming";
        if ((nextDay.getTemp() < currentDay.getTemp()) && Math.abs(sub) >= 5)
            triggerName = "coldSnap";
        return triggerName;
    }

    private Long getTemperatureSubtract(WeatherTriggerRelation currentDay, WeatherTriggerRelation nextDay) {
        return Math.subtractExact(currentDay.getTemp().longValue(), nextDay.getTemp().longValue());
    }


    private Trigger findTriggerByTriggerName(String name) {
        return triggerRepository.getTriggerByName(name.toLowerCase())
                .orElseThrow(()->new TriggerNotFoundException(name));
    }

}
