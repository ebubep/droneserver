package com.dronelab.droneserver.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PayloadMetaDTO(
        @JsonProperty("drone_id")
        long droneId,

        @JsonProperty("operator_on_duty")
        String operatorName,

        @JsonProperty("payload")
        List<PayloadBodyDTO> payloadList


) {
}

