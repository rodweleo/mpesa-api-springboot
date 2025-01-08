package com.streetcoders.mpesaApi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StkPushReqDTO {

    @JsonProperty("BusinessShortCode")
    private String BusinessShortCode;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("Timestamp")
    private String Timestamp;

    @JsonProperty("TransactionType")
    private String TransactionType;

    @NotNull(message = "Amount is required")
    @JsonProperty("Amount")
    private String Amount;

    @JsonProperty("PartyA")
    private String PartyA;

    @JsonProperty("PartyB")
    private String PartyB;

    @NotNull(message = "The phone number is required")
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;

    @JsonProperty("CallBackURL")
    private String CallBackURL;

    @JsonProperty("AccountReference")
    private String AccountReference;

    @JsonProperty("TransactionDesc")
    private String TransactionDesc;

}
