package com.streetcoders.mpesaApi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StkPushResponseDTO {

    @NotBlank(message = "MerchantRequestID is required")
    @JsonProperty("MerchantRequestID")
    private String merchantRequestID;

    @NotBlank(message = "CheckoutRequestID is required")
    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestID;

    @NotBlank(message = "ResponseCode is required")
    @JsonProperty("ResponseCode")
    private String responseCode;

    @NotBlank(message = "ResponseDescription is required")
    @JsonProperty("ResponseDescription")
    private String responseDescription;

    @NotBlank(message = "CustomerMessage is required")
    @JsonProperty("CustomerMessage")
    private String customerMessage;

}
