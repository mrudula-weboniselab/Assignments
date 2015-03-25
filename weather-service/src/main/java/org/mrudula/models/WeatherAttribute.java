package org.mrudula.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by webonise on 23-03-2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAttribute {
    @JsonProperty
    private String id;
    @JsonProperty
    private String main;
    @JsonProperty
    private String description;
    @JsonProperty
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String idWeather) {
        this.id = idWeather;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String mainWeather) {
        this.main = mainWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptionWeather) {
        this.description = descriptionWeather;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String iconWeather) {
        this.icon = iconWeather;
    }
}