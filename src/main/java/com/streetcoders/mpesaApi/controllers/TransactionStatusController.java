package com.streetcoders.mpesaApi.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.streetcoders.mpesaApi.dtos.AccessTokenResponseDTO;
import com.streetcoders.mpesaApi.dtos.TransactionStatusResponseDTO;
import com.streetcoders.mpesaApi.services.AuthService;
import com.streetcoders.mpesaApi.services.TransactionStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TransactionStatusController {

    private final TransactionStatusService transactionStatusService;

    public TransactionStatusController(TransactionStatusService transactionStatusService) {
        this.transactionStatusService = transactionStatusService;
    }

    @PostMapping("/check-transaction-status")
    public ResponseEntity<TransactionStatusResponseDTO> checkTransactionStatus(@RequestBody JsonNode transactionStatusReqBody) {
        try{
            String transactionId = transactionStatusReqBody.get("transactionId").toString();
            TransactionStatusResponseDTO transactionStatusResponseDTO = transactionStatusService.checkTransactionStatus(transactionId);
            return ResponseEntity.ok(transactionStatusResponseDTO);
        }catch(RuntimeException | IOException ex){
            return ResponseEntity.badRequest().build();
        }
    }

}
