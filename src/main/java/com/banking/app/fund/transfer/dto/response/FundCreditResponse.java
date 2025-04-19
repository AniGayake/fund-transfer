package com.banking.app.fund.transfer.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FundCreditResponse {
    private String status;
    private String accountNumber;
    private String transactionId;
    private BigDecimal balanceAfterCredit;
    private BigDecimal creditedAmount;
    private String currency;
    private String message;
    private LocalDateTime timestamp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getCreditedAmount() {
        return creditedAmount;
    }

    public void setCreditedAmount(BigDecimal creditedAmount) {
        this.creditedAmount = creditedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalanceAfterCredit() {
        return balanceAfterCredit;
    }

    public void setBalanceAfterCredit(BigDecimal balanceAfterCredit) {
        this.balanceAfterCredit = balanceAfterCredit;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FundCreditResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", creditedAmount=" + creditedAmount +
                ", currency='" + currency + '\'' +
                ", balanceAfterCredit=" + balanceAfterCredit +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
