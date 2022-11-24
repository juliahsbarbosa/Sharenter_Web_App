package com.project.sharenter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geocoding {
    private Double lat;
    private Double lng;

}