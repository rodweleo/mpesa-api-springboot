package com.streetcoders.mpesaApi.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streetcoders.mpesaApi.common.config.MpesaConfig;
import com.streetcoders.mpesaApi.dtos.AccessTokenResponseDTO;
import com.streetcoders.mpesaApi.dtos.DynamicQrCodeReqDTO;
import com.streetcoders.mpesaApi.dtos.DynamicQrCodeResponseDTO;
import com.streetcoders.mpesaApi.services.AuthService;
import com.streetcoders.mpesaApi.services.DynamicQrCodeService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class DynamicQrCodeServiceImpl implements DynamicQrCodeService {

    private final MpesaConfig mpesaConfig;
    private final OkHttpClient okHttpClient;
    private final AuthService authService;
    private final ObjectMapper objectMapper;

    public DynamicQrCodeServiceImpl(MpesaConfig mpesaConfig, OkHttpClient okHttpClient, AuthService authService, ObjectMapper objectMapper) {
        this.mpesaConfig = mpesaConfig;
        this.okHttpClient = okHttpClient;
        this.authService = authService;
        this.objectMapper = objectMapper;
    }

    @Override
    public DynamicQrCodeResponseDTO generateQrCode(DynamicQrCodeReqDTO dynamicQrCodeReqDTO) throws JsonProcessingException {

        log.info("Generating the QR code for the following information: {}", objectMapper.writeValueAsString(dynamicQrCodeReqDTO));

        String jsonRequestBody = objectMapper.writeValueAsString(dynamicQrCodeReqDTO);

            RequestBody requestBody = RequestBody.create(jsonRequestBody, MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(mpesaConfig.getDynamicQrCodeEndpoint())
                    .post(requestBody)
                    .addHeader("Authorization", "Bearer " + authService.generateAccessToken().getAccessToken())
                    .addHeader("Content-Type", "application/json")
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
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
                return objectMapper.readValue(responseBody, DynamicQrCodeResponseDTO.class);

            } catch (IOException e) {
                log.error("Error occurred while fetching the QR token: {}", e.getMessage(), e);
                return null;
            }
    }

}
