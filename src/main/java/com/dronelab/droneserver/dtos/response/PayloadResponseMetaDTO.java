package com.dronelab.droneserver.dtos.response;

import com.dronelab.droneserver.dtos.request.PayloadBodyDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record PayloadResponseMetaDTO(
        @JsonProperty("drone_id")
        long droneId,

        @JsonProperty("operator_on_duty")
        String operatorName,

        Integer battery,

        Integer model,

        LocalDateTime loadEndDate,

        @JsonProperty("payload")
        List<PayloadBodyDTO> payloadList
) {
}

