package com.streetcoders.mpesaApi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mpesa.daraja")
@Data
public class MpesaConfig {
    private String consumerKey;
    private String consumerSecret;
    private String grantType;
    private String oauthEndpoint;
    private String businessShortCode;
    private String responseType;
    private String stkPushPassKey;
    private String confirmationUrl;
    private String validationUrl;
    private String urlRegistrationEndpoint;
    private String c2bTransactionEndpoint;
    private String b2cTransactionEndpoint;
    private String stkPushEndpoint;
    private String dynamicQrCodeEndpoint;

    public String toString() {
        return "MpesaConfig{" +
                "consumerKey='" + consumerKey + '\'' +
                ", consumerSecret='" + consumerSecret + '\'' +
                ", grantType='" + grantType + '\'' +
                ", oauthTokenUrl='" + oauthEndpoint + '\'' +
                '}';
    }
}