package com.streetcoders.mpesaApi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streetcoders.mpesaApi.common.config.MpesaConfig;
import com.streetcoders.mpesaApi.dtos.AccessTokenResponseDTO;
import com.streetcoders.mpesaApi.services.AuthService;
import com.streetcoders.mpesaApi.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import okhttp3.*;

import java.io.IOException;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final OkHttpClient okHttpClient;
    private final MpesaConfig mpesaConfig;
    private final ObjectMapper objectMapper;

    public AuthServiceImpl(OkHttpClient okHttpClient, MpesaConfig mpesaConfig, ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient;
        this.mpesaConfig = mpesaConfig;
        this.objectMapper = objectMapper;
    }

    @Override
    public AccessTokenResponseDTO generateAccessToken(){
        String encodedCredentials = Utils.toBase64(mpesaConfig.getConsumerKey() + ":" + mpesaConfig.getConsumerSecret());
        String accessTokenUrl = String.format("%s?grant_type=%s", mpesaConfig.getOauthEndpoint(), mpesaConfig.getGrantType());
        Request request = new Request.Builder()
                .url(accessTokenUrl)
                .get()
                .addHeader("Authorization", "Basic " + encodedCredentials)
                .addHeader("Content-Type", "application/json")
                .build();

        log.info("Sending access token generation request to URL: {}", accessTokenUrl);
        log.info("Encoded credentials: {}", encodedCredentials);

        try{

            Response response = okHttpClient.newCall(request).execute();

            if (!response.isSuccessful()) {

                log.error("Request failed with status code: {}", response.code());
                log.error("Error: {}", response.message());

                return null;
            }

            String responseBody = response.body().string();

            if (responseBody.isEmpty()) {
                log.error("Received empty response body");
                return null;
            }

            log.info("Received response: {}", responseBody);

            return objectMapper.readValue(responseBody, AccessTokenResponseDTO.class);
        } catch (IOException e) {
            log.error("Error occurred while fetching access token: {}", e.getMessage());
            return null;
        }

    }

}
