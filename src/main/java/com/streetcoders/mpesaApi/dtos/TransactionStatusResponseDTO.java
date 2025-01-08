package com.streetcoders.mpesaApi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransactionStatusResponseDTO {

    @NotBlank(message = "OriginatorConversationID is required")
    @JsonProperty("OriginatorConversationID")
    private String OriginatorConversationID;

    @NotBlank(message = "ConversationID is required")
    @JsonProperty("ConversationID")
    private String ConversationID;

    @NotBlank(message = "ResponseCode is required")
    @JsonProperty("ResponseCode")
    private String responseCode;

    @NotBlank(message = "ResponseDescription is required")
    @JsonProperty("ResponseDescription")
    private String ResponseDescription;

}
