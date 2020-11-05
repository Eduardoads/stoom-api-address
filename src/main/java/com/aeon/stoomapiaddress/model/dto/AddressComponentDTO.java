package com.aeon.stoomapiaddress.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddressComponentDTO {

    @JsonProperty("long_name")
    String longName;

    @JsonProperty("short_name")
    String shortName;

    List<String> types;

    public AddressComponentDTO() {
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
