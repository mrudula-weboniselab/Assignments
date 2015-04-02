package org.mrudula.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Created by webonise on 23-03-2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResult {
    @JsonProperty
    private CoordAttribute coord;
    @JsonProperty
    private SysAttribute sys;
    @JsonProperty
    private WeatherAttribute[] weather;
    @JsonProperty
    private String base;
    @JsonProperty
    private MainAttribute main;
    @JsonProperty
    private WindAttribute wind;
    @JsonProperty
    private String dt;
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;


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

    public String getDt() {
        return dt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



}
