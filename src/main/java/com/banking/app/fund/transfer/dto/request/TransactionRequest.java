package com.banking.app.fund.transfer.dto.request;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class TransactionRequest {
    @NonNull
    private String accountNumber;
    private Long customerId;
    @NonNull
    private BigDecimal amount;                   // Credit amount
    private String currency;
    private String referenceId;             // Unique transaction reference ID (for idempotency)
    private String externalReferenceId;     // External system reference (optional)
    private String narration;               // Message shown to customer (e.g., "April Salary")
    private String remarks;
    private String initiatedBy;
    private LocalDateTime timestamp;               // ISO8601 timestamp of initiation
    private String channel;                 // UPI, BRANCH, ONLINE, ATM, etc.
    private String location;
    private String transactionType;
    private String purposeCode;
    private Boolean isReversal;

    @NonNull
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NonNull String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @NonNull
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NonNull BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getExternalReferenceId() {
        return externalReferenceId;
    }

    public void setExternalReferenceId(String externalReferenceId) {
        this.externalReferenceId = externalReferenceId;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean getReversal() {
        return isReversal;
    }

    public void setReversal(Boolean reversal) {
        isReversal = reversal;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", externalReferenceId='" + externalReferenceId + '\'' +
                ", narration='" + narration + '\'' +
                ", remarks='" + remarks + '\'' +
                ", initiatedBy='" + initiatedBy + '\'' +
                ", timestamp=" + timestamp +
                ", channel='" + channel + '\'' +
                ", location='" + location + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", purposeCode='" + purposeCode + '\'' +
                ", isReversal=" + isReversal +
                '}';
    }
}
