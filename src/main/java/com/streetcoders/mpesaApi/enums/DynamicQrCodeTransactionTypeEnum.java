package com.streetcoders.mpesaApi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DynamicQrCodeTransactionTypeEnum {

    BG("Pay Merchant (Buy Goods)"),
    WA("Withdraw Cash at Agent Till"),
    PB("Paybill or Business number"),
    SM("Send Money (Mobile number)"),
    SB("Sent to Business");

    private final String description;

    public static DynamicQrCodeTransactionTypeEnum fromAbbreviation(String abbreviation) {
        return Arrays.stream(DynamicQrCodeTransactionTypeEnum.values())
                .filter(param -> param.name().equalsIgnoreCase(abbreviation))
                .findFirst()
                .orElse(null);
    }

}
