package com.banking.app.fund.transfer.bo;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Account {
    @Id
    private String accountNumber;
    protected Long customerId;
    protected String accountTypeCode;
    protected String branchCode;
    protected String ifscCode;
    protected BigDecimal balance;
    protected String currency;
    protected LocalDateTime createdAt;
    protected String statusCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Account{" +
                "customerId=" + customerId +
                ", accountTypeCode='" + accountTypeCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", createdAt=" + createdAt +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
