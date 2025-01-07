package com.streetcoders.mpesaApi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.streetcoders.mpesaApi.enums.DynamicQrCodeTransactionTypeEnum;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class DynamicQrCodeReqDTO {

    @JsonProperty("MerchantName")
    private String merchantName;

    @JsonProperty("RefNo")
    private String refNo;

    @NotNull(message = "Amount is required")
    @JsonProperty("Amount")
    private Integer amount;

    @NotNull(message = "Transaction type is required")
    @JsonProperty("TrxCode")
    private DynamicQrCodeTransactionTypeEnum trxCode;

    @JsonProperty("CPI")
    private String cpi;

    @JsonProperty("Size")
    private String size;

}
