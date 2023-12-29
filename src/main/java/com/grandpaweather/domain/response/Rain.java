package com.grandpaweather.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rain {

    @JsonProperty("1h")
    private double oneHour;
    @JsonProperty("3h")
    private double threeHour;

}
