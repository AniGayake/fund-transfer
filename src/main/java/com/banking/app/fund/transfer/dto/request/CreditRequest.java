package com.banking.app.fund.transfer.dto.request;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreditRequest {
    @NonNull
    private String accountNumber;           // Target account number
    @NonNull
    private BigDecimal amount;                   // Credit amount
    private String currency;                 // ISO currency code (e.g., INR, USD)
    private String creditSource;            // Type of credit (SALARY, REFUND, UPI, NEFT, CASH, etc.)
    private String referenceId;             // Unique transaction reference ID (for idempotency)
    private String externalReferenceId;     // External system reference (optional)
    private String narration;               // Message shown to customer (e.g., "April Salary")
    private String remarks;                 // Internal remarks (optional)
    private String initiatedBy;             // User/system initiating the transaction
    private String approvedBy;              // Optional: For branch approval flow
    private LocalDateTime timestamp;               // ISO8601 timestamp of initiation
    private String channel;                 // UPI, BRANCH, ONLINE, ATM, etc.
    private Long customerId;              // Customer identifier (helps in cross-account mapping)
    private String location;                // Branch or ATM location (optional)
    private String mode;                    // CASH, CHEQUE, ONLINE, etc.
    private String transactionType;         // CREDIT (useful if unified request model is used)
    private String purposeCode;             // For compliance/reporting (e.g., INCOME, LOAN_REPAYMENT)
    private String sourceInstitutionCode;   // For interbank transfers or APIs like NEFT/IMPS
    private Boolean isReversal;             // To handle reversal transactions

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
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

    public String getCreditSource() {
        return creditSource;
    }

    public void setCreditSource(String creditSource) {
        this.creditSource = creditSource;
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

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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
        return "CreditRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", creditSource='" + creditSource + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", externalReferenceId='" + externalReferenceId + '\'' +
                ", narration='" + narration + '\'' +
                ", remarks='" + remarks + '\'' +
                ", initiatedBy='" + initiatedBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", timestamp=" + timestamp +
                ", channel='" + channel + '\'' +
                ", customerId='" + customerId + '\'' +
                ", location='" + location + '\'' +
                ", mode='" + mode + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", purposeCode='" + purposeCode + '\'' +
                ", sourceInstitutionCode='" + sourceInstitutionCode + '\'' +
                ", isReversal=" + isReversal +
                '}';
    }
}
