package com.streetcoders.mpesaApi.services;

import com.streetcoders.mpesaApi.dtos.StkPushReqDTO;
import com.streetcoders.mpesaApi.dtos.StkPushResponseDTO;

import java.io.IOException;

public interface StkPushService {
    StkPushResponseDTO initiateSTKPush(String amount, String phoneNumber) throws IOException;
}
