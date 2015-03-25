package org.mrudula.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by webonise on 23-03-2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainAttribute {
    @JsonProperty
    private String temp;
    @JsonProperty
    private String pressure;
    @JsonProperty
    private String humidity;
    @JsonProperty
    private String temp_min;
    @JsonProperty
    private String temp_max;
    @JsonProperty
    private String sea_level;
    @JsonProperty
    private String grnd_level;


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String tempMin) {
        this.temp_min = tempMin;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String tempMax) {
        this.temp_max = tempMax;
    }

    public String getSea_level() {
        return sea_level;
    }

    public String getGrnd_level() { return grnd_level; }


}
