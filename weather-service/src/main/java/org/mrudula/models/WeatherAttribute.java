package org.mrudula.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by webonise on 23-03-2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAttribute {
    private String id;
    private String main;
    private String description;
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