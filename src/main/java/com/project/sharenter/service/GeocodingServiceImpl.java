package com.project.sharenter.service;

import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResponse;

import com.project.sharenter.model.Geocoding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.maps.GeoApiContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class GeocodingServiceImpl implements GeocodingService {

    private GeoApiContext geoApiContext;


    @Autowired
    public GeocodingServiceImpl(@Value("${maps.api.key}") String mapsKey) {
        geoApiContext = new GeoApiContext.Builder().apiKey(mapsKey)
                .maxRetries(2)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Optional<Geocoding> computeGeoLocation(String address) {
        final PlacesSearchResponse placesSearchResponse;
        try {
            placesSearchResponse = PlacesApi.textSearchQuery(geoApiContext,
                    address).await();
            log.info("Processing address line using PlacesApi.textSearchQuery {}", address);
            if (placesSearchResponse != null && placesSearchResponse.results.length > 0) {
                log.info("Obtained following predictions using PlacesApi.textSearchQuery {}",
                        Arrays.toString(placesSearchResponse.results));
                final GeocodingResult[] geocodingResults = GeocodingApi.newRequest(geoApiContext)
                        .place(placesSearchResponse.results[0].placeId)
                        .await();
                log.info("Processing address line using GeocodingApi.newRequest {}", address);
                if (geocodingResults != null && geocodingResults.length > 0) {
                    log.info("Obtained following geocoding results using GeocodingApi.newRequest {}",
                            Arrays.toString(geocodingResults));
                    final String placeId = geocodingResults[0].placeId;
                    final double latitude = geocodingResults[0].geometry.location.lat;
                    final double longitude = geocodingResults[0].geometry.location.lng;
                    final Geocoding geoLocation = new Geocoding(latitude, longitude);
                    log.info("Computed following coordinates using GeocodingApi.newRequest {}", geoLocation);
                    return Optional.of(geoLocation);
                } else {
                    log.warn("No coordinates found using GeocodingApi.newRequest {}", address);
                }
            } else {
                log.warn("No coordinates found using PlacesApi.textSearchQuery {}", address);
            }
        } catch (ApiException | InterruptedException | IOException e) {
            log.error("Encountered error [{}] using GoogleMapsApi for address {} : {}", e.getMessage(), address, e);
        }

        return Optional.empty();
    }

}
