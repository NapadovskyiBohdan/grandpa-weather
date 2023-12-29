package com.grandpaweather.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemResponse {
    private int type;
    private long id;
    private String country;
    private long sunrise;
    private long sunset;
    private String pod;
}
