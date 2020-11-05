package com.aeon.stoomapiaddress.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResultDTO {

    List<GeocodeObjectDTO> results;
    String status;

    public GeocodeResultDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeocodeObjectDTO> getResults() {
        return results;
    }

    public void setResults(List<GeocodeObjectDTO> results) {
        this.results = results;
    }
}
