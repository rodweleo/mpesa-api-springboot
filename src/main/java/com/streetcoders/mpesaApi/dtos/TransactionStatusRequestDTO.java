package com.streetcoders.mpesaApi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransactionStatusRequestDTO {

    @NotBlank(message = "Initiator is required")
    @JsonProperty("Initiator")
    private String initiator;

    @NotBlank(message = "SecurityCredential is required")
    @JsonProperty("SecurityCredential")
    private String securityCredential;

    @NotBlank(message = "Command ID is required")
    @JsonProperty("Command ID")
    private String commandId;

    @NotBlank(message = "Transaction ID is required")
    @JsonProperty("Transaction ID")
    private String transactionId;

    @NotBlank(message = "OriginatorConversationID is required")
    @JsonProperty("OriginatorConversationID")
    private String originatorConversationId;

    @NotBlank(message = "PartyA is required")
    @JsonProperty("PartyA")
    private String partyA;

    @NotBlank(message = "IdentifierType is required")
    @JsonProperty("IdentifierType")
    private String identifierType;

    @NotBlank(message = "ResultURL is required")
    @JsonProperty("ResultURL")
    private String resultUrl;

    @NotBlank(message = "QueueTimeOutURL is required")
    @JsonProperty("QueueTimeOutURL")
    private String queueTimeOutUrl;

    @NotBlank(message = "Remarks is required")
    @JsonProperty("Remarks")
    private String remarks;

    @JsonProperty("Occasion")
    private String occasion;

}
