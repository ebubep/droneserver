
package com.dronelab.droneserver.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DroneDTO(
        String serial,
        Integer model,
        Integer state,
        Integer battery,
        @JsonProperty("weight_limit")
        Integer weightLimit) {

}
