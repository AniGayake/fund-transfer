package com.banking.app.fund.transfer.dto.response;

import java.time.LocalDateTime;

public abstract class TransactionResponse {
    private String status;
    private String accountNumber;
    private String transactionId;
    private String currency;
    private String message;
    private LocalDateTime timestamp;
    private String transactionType;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "status='" + status + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", currency='" + currency + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
