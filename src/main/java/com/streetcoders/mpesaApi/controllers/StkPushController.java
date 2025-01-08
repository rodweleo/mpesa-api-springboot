package com.streetcoders.mpesaApi.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.streetcoders.mpesaApi.dtos.AccessTokenResponseDTO;
import com.streetcoders.mpesaApi.dtos.StkPushReqDTO;
import com.streetcoders.mpesaApi.dtos.StkPushResponseDTO;
import com.streetcoders.mpesaApi.services.StkPushService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class StkPushController {

    public final StkPushService stkPushService;

    public StkPushController(StkPushService stkPushService) {
        this.stkPushService = stkPushService;
    }

    @PostMapping("/initiate-stk-push")
    public ResponseEntity<StkPushResponseDTO> generateAccessToken(@RequestBody JsonNode requestBody) {
        try{
            // Extract the amount and phone number from the request body
            String amount = requestBody.get("amount").asText();
            String phoneNumber = requestBody.get("phoneNumber").asText();

            if(amount.isEmpty()){
                return null;
            }

            if(phoneNumber.isEmpty()){
                return null;
            }

            StkPushResponseDTO stkPushResponseDTO = stkPushService.initiateSTKPush(amount, phoneNumber);
            return ResponseEntity.ok(stkPushResponseDTO);
        }catch(RuntimeException | IOException ex){
            return ResponseEntity.badRequest().build();
        }
    }

}
