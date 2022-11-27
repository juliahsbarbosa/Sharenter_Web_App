package com.project.sharenter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalkScore {
    @JsonProperty("walkscore")
    private String walkscore;

    @JsonProperty("description")
    private String description;

}
