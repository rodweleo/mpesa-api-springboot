package com.streetcoders.mpesaApi.dtos;

import lombok.Data;

@Data
public class DynamicQrCodeResponseDTO {

    private String responseCode;
    private String requestId;
    private String responseDescription;
    private String qrCode;

}
