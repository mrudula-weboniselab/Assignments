package org.mrudula.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by webonise on 23-03-2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysAttribute {
    @JsonProperty
    private String type;
    @JsonProperty
    private String id;
    @JsonProperty
    private String message;
    @JsonProperty
    private String country;
    @JsonProperty
    private String sunrise;
    @JsonProperty
    private String sunset;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

}
