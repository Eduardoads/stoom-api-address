package com.aeon.stoomapiaddress.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeGeometryDTO {

    @JsonProperty("location")
    GeocodeLocationDTO geocodeLocation;

    public GeocodeGeometryDTO() {
    }

    public GeocodeLocationDTO getGeocodeLocation() {
        return geocodeLocation;
    }

    public void setGeocodeLocation(GeocodeLocationDTO geocodeLocation) {
        this.geocodeLocation = geocodeLocation;
    }
}
