package com.streetcoders.mpesaApi.controllers;

import com.streetcoders.mpesaApi.dtos.AccessTokenResponseDTO;
import com.streetcoders.mpesaApi.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/generate-access-token")
    public ResponseEntity<AccessTokenResponseDTO> generateAccessToken() {
        try{
            AccessTokenResponseDTO accessTokenResponseDTO = authService.generateAccessToken();
            return ResponseEntity.ok(accessTokenResponseDTO);
        }catch(RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

}
