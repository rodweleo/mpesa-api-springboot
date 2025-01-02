package com.streetcoders.mpesaApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MpesaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpesaApiApplication.class, args);
	}

	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
