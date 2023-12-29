package com.grandpaweather.domain.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GenericResponse {

    public int status;
    public String statusText;
    public String message;
    public Object detail;
    public String url;
    public HttpStatus httpStatus;



    public static GenericResponse buildError(HttpStatus httpStatus,  String errorMessage, Object detail, String url) {
        return GenericResponse.builder()
                .status(httpStatus.value())
                .statusText(httpStatus.getReasonPhrase())
                .message(errorMessage)
                .detail(detail)
                .url(url)
                .httpStatus(httpStatus)
                .build();

    }



}
