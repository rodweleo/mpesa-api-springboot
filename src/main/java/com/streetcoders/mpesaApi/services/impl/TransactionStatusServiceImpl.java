package com.streetcoders.mpesaApi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streetcoders.mpesaApi.common.config.MpesaConfig;
import com.streetcoders.mpesaApi.dtos.TransactionStatusRequestDTO;
import com.streetcoders.mpesaApi.dtos.TransactionStatusResponseDTO;
import com.streetcoders.mpesaApi.services.AuthService;
import com.streetcoders.mpesaApi.services.TransactionStatusService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TransactionStatusServiceImpl implements TransactionStatusService {

    public final AuthService authService;
    public final ObjectMapper objectMapper;
    public final MpesaConfig mpesaConfig;

    public TransactionStatusServiceImpl(AuthService authService, ObjectMapper objectMapper, MpesaConfig mpesaConfig) {
        this.authService = authService;
        this.objectMapper = objectMapper;
        this.mpesaConfig = mpesaConfig;
    }

    @Override
    public TransactionStatusResponseDTO checkTransactionStatus(String transactionId) throws IOException {

        log.info("Checking transaction status for transaction id: {}", transactionId);

        TransactionStatusRequestDTO transactionStatusRequestDTO = new TransactionStatusRequestDTO();
        transactionStatusRequestDTO.setTransactionId(transactionId);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        String jsonRequestBody = objectMapper.writeValueAsString(transactionStatusRequestDTO);
        RequestBody body = RequestBody.create(jsonRequestBody, mediaType);
        Request request = new Request.Builder()
                .url(mpesaConfig.getTransactionStatusEndpoint())
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + authService.generateAccessToken().getAccessToken())
                .build();

        log.info("Sending transaction status request to {} with data: {}", mpesaConfig.getTransactionStatusEndpoint(), jsonRequestBody);

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {

            String errorBody = response.body().string();
            log.error("Request failed with status code: {}", response.code());
            log.error("Error message: {}", response.message());
            log.error("Error body: {}", errorBody);
            return null;

        }

        String responseBody = response.body().string();

        if (responseBody.isEmpty()) {
            log.error("Received empty response body");
            return null;
        }

        log.info("Received response: {}", responseBody);
        return objectMapper.readValue(responseBody, TransactionStatusResponseDTO.class);

    };
}
