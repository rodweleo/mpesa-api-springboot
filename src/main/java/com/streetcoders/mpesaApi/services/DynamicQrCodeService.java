package com.streetcoders.mpesaApi.services;

import com.streetcoders.mpesaApi.dtos.DynamicQrCodeReqDTO;
import com.streetcoders.mpesaApi.dtos.DynamicQrCodeResponseDTO;

public interface DynamicQrCodeService {

    DynamicQrCodeResponseDTO generateQrCode(DynamicQrCodeReqDTO dynamicQrCodeReqDTO);
}
