package com.dronelab.droneserver.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DroneCheckDTO(
        @JsonProperty("drone_id")
        long droneId
) {
}
