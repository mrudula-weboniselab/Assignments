package org.mrudula.utils;

import org.mrudula.models.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Created by webonise on 23-03-2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResult {
    private CoordAttribute coord;
    private SysAttribute sys;
    private WeatherAttribute[] weather;
    private String base;
    private MainAttribute main;
    private WindAttribute wind;
    private CloudsAttribute clouds;
    private String dt;
    private String id;
    private String name;
    private String cod;

    public CoordAttribute getCoord() {
        return coord;
    }

    public SysAttribute getSys() {
        return sys;
    }

    public WeatherAttribute[] getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public MainAttribute getMain() {
        return main;
    }

    public WindAttribute getWind() {
        return wind;
    }

    public CloudsAttribute getClouds() {
        return clouds;
    }

    public String getDt() {
        return dt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCod() {
        return cod;
    }

}
