package com.streetcoders.mpesaApi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streetcoders.mpesaApi.common.config.MpesaConfig;
import com.streetcoders.mpesaApi.dtos.DynamicQrCodeResponseDTO;
import com.streetcoders.mpesaApi.dtos.StkPushReqDTO;
import com.streetcoders.mpesaApi.dtos.StkPushResponseDTO;
import com.streetcoders.mpesaApi.services.AuthService;
import com.streetcoders.mpesaApi.services.StkPushService;
import com.streetcoders.mpesaApi.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class StkPushServiceImpl implements StkPushService {

    public final AuthService authService;
    public final ObjectMapper objectMapper;
    public final MpesaConfig mpesaConfig;

    public StkPushServiceImpl(AuthService authService, ObjectMapper objectMapper, MpesaConfig mpesaConfig) {
        this.authService = authService;
        this.objectMapper = objectMapper;
        this.mpesaConfig = mpesaConfig;
    }

    @Override
    public StkPushResponseDTO initiateSTKPush(String amount, String phoneNumber) throws IOException {

        log.info("Initiating the stk push for amount: {} to phone number: {}", amount, phoneNumber);

        String accessToken = authService.generateAccessToken().getAccessToken();
        String timestamp = generateStkPushTimestamp();
        String password = generateStkPushPassword(timestamp);

        //modify the request to include the generated password and timestamp;
        StkPushReqDTO stkPushReqDTO = new StkPushReqDTO();
        stkPushReqDTO.setBusinessShortCode(mpesaConfig.getBusinessShortCode());
        stkPushReqDTO.setPassword(password);
        stkPushReqDTO.setTimestamp(timestamp);
        stkPushReqDTO.setTransactionType("CustomerPayBillOnline");
        stkPushReqDTO.setAmount(amount);
        stkPushReqDTO.setPartyA(phoneNumber);
        stkPushReqDTO.setPartyB(mpesaConfig.getBusinessShortCode());
        stkPushReqDTO.setCallBackURL("https://mydomain.com/pat");
        stkPushReqDTO.setPhoneNumber(phoneNumber);
        stkPushReqDTO.setAccountReference(phoneNumber);
        stkPushReqDTO.setTransactionDesc("Purchasing Ascecoin");

        String jsonRequestBody = objectMapper.writeValueAsString(stkPushReqDTO);

        log.info("The stk push request body: {}", jsonRequestBody);

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(jsonRequestBody, mediaType);

        Request request = new Request.Builder()
                .url(mpesaConfig.getStkPushEndpoint())
                .post(body)
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", "application/json")
                .build();

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
        return objectMapper.readValue(responseBody, StkPushResponseDTO.class);

    }

    // Method to generate the password for the STK Push
    private String generateStkPushPassword(String timestamp) {
        String passwordString = mpesaConfig.getBusinessShortCode() + mpesaConfig.getStkPushPassKey() + timestamp;
        return Utils.toBase64(passwordString);
    }

    private String generateStkPushTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

}
