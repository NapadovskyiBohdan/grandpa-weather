package com.grandpaweather.helper;

import com.grandpaweather.domain.request.WeatherRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class URLGenerator {

    @Value("${openweather.api.key}")
    private String API_KEY;
    @Value("${openweather.api.url}")
    private String API_URL;
    private static final Logger log = LoggerFactory.getLogger(URLGenerator.class);

    public String generateURL(WeatherRequest request) {
        log.info("Build URL according request {}", request);
        return new StringBuilder(API_URL)
                .append("/forecast")
                .append("?appid=").append(this.API_KEY)
                .append(getCoordinatesParams(request))
                .append(getQueryParams(request))
                .append(getLanguageParam(request))
                .append(getUnitsParam(request))
                .toString();
    }

    private String getCoordinatesParams(WeatherRequest request) {
        if (request.getLat() != 0 && request.getLon() != 0) {
            return "&lat=" + request.getLat() + "&lon=" + request.getLon();
        }
        return "";
    }

    private String getQueryParams(WeatherRequest request) {
        if (request.getCity() != null || request.getCountryCode() != null) {
            return "&q=" + request.getCity() + "," + request.getCountryCode();
        }
        return "";
    }

    private String getLanguageParam(WeatherRequest request) {
        return request.getLang() != null ? "&lang=" + request.getLang() : "";
    }

    private String getUnitsParam(WeatherRequest request) {
        return request.getUnits() != null ? "&units=" + request.getUnits() : "";
    }


}
