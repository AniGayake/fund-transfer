package com.banking.app.fund.transfer.bo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private String accountNumber;
    private String transactionType;
    private BigDecimal transactionAmount;
    private String currency;
    private String description;
    private String status;
    private String channel;
    private Long customerId;
    private String location;
    private String mode;
    private LocalDateTime updatedAt;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
    private String purposeCode;
    private String sourceInstitutionCode;
    private Boolean isReversal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public String getSourceInstitutionCode() {
        return sourceInstitutionCode;
    }

    public void setSourceInstitutionCode(String sourceInstitutionCode) {
        this.sourceInstitutionCode = sourceInstitutionCode;
    }

    public Boolean getReversal() {
        return isReversal;
    }

    public void setReversal(Boolean reversal) {
        isReversal = reversal;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", currency='" + currency + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", channel='" + channel + '\'' +
                ", customerId=" + customerId +
                ", location='" + location + '\'' +
                ", mode='" + mode + '\'' +
                ", updatedAt=" + updatedAt +
                ", transactionDate=" + transactionDate +
                ", createdAt=" + createdAt +
                ", purposeCode='" + purposeCode + '\'' +
                ", sourceInstitutionCode='" + sourceInstitutionCode + '\'' +
                ", isReversal=" + isReversal +
                '}';
    }
}
