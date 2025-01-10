package com.dronelab.droneserver.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PayloadBodyDTO(
        @JsonProperty("med_code")
        String medicineCode,

        Integer quantity
) {

}
