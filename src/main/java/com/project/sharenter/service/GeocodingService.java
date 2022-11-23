package com.project.sharenter.service;

import com.project.sharenter.model.Geocoding;

import java.util.Optional;

public interface GeocodingService {
    Optional<Geocoding> computeGeoLocation(String address);
}
