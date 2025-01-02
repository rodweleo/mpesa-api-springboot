package com.streetcoders.mpesaApi.services;

import com.streetcoders.mpesaApi.dtos.AccessTokenResponseDTO;

public interface AuthService {
    AccessTokenResponseDTO generateAccessToken();
}
