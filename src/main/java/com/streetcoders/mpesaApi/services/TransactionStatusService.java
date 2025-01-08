package com.streetcoders.mpesaApi.services;

import com.streetcoders.mpesaApi.dtos.TransactionStatusResponseDTO;

import java.io.IOException;

public interface TransactionStatusService {
    TransactionStatusResponseDTO checkTransactionStatus(String transactionId) throws IOException;
}
