package com.grandpaweather.web.controller;

import com.grandpaweather.domain.dto.WeatherTriggerDTO;
import com.grandpaweather.domain.request.WeatherRequest;
import com.grandpaweather.service.WeatherService;
import com.grandpaweather.service.impl.WeatherTriggerBuilderImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    private static final Logger log = LoggerFactory.getLogger(WeatherTriggerBuilderImpl.class);

    @GetMapping("/weather")
    public ResponseEntity<List<WeatherTriggerDTO>> updateTriggersAccordingRequest(WeatherRequest request) {
        return ResponseEntity.ok().body(weatherService.getWeatherForCurrentDayByRequest(request));
    }


}
