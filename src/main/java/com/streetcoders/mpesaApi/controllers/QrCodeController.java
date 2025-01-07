package com.streetcoders.mpesaApi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.streetcoders.mpesaApi.dtos.AccessTokenResponseDTO;
import com.streetcoders.mpesaApi.dtos.DynamicQrCodeReqDTO;
import com.streetcoders.mpesaApi.dtos.DynamicQrCodeResponseDTO;
import com.streetcoders.mpesaApi.services.AuthService;
import com.streetcoders.mpesaApi.services.DynamicQrCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrCodeController {

    private final DynamicQrCodeService dynamicQrCodeService;

    public QrCodeController(DynamicQrCodeService dynamicQrCodeService) {
        this.dynamicQrCodeService = dynamicQrCodeService;
    }

    @PostMapping("/generate-dynamic-qr-code")
    public ResponseEntity<DynamicQrCodeResponseDTO> generateDynamicQrCode(@RequestBody DynamicQrCodeReqDTO dynamicQrCodeReqDTO) {
        try{
            DynamicQrCodeResponseDTO dynamicQrCodeResponseDTO = dynamicQrCodeService.generateQrCode(dynamicQrCodeReqDTO);
            return ResponseEntity.ok(dynamicQrCodeResponseDTO);
        }catch(RuntimeException | JsonProcessingException ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
