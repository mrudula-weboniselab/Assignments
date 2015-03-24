package org.mrudula.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by webonise on 23-03-2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudsAttribute {
    private String all;
    public String getAll() {
        return all;
    }

}
