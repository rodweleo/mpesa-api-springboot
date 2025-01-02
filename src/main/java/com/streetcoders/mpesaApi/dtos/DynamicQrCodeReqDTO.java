package com.streetcoders.mpesaApi.dtos;

import lombok.Data;

@Data
public class DynamicQrCodeReqDTO {

    private String merchantName;

    private String refNo;

    private Integer amount;

    private String trxCode;

    private String cpi;

    private String size;

}
