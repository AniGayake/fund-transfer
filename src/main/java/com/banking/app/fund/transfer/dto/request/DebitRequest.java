package com.banking.app.fund.transfer.dto.request;

import java.math.BigDecimal;

public class DebitRequest extends TransactionRequest{
    private String debitMode;
    private AuthDetails auth;
    private String initiatedBy;
    private BigDecimal feeCharged;
    private BeneficiaryDetails beneficiaryDetails;

    public String getDebitMode() {
        return debitMode;
    }
    public void setDebitMode(String debitMode) {
        this.debitMode = debitMode;
    }
    public AuthDetails getAuth() {
        return auth;
    }

    public void setAuth(AuthDetails auth) {
        this.auth = auth;
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public BigDecimal getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(BigDecimal feeCharged) {
        this.feeCharged = feeCharged;
    }

    public BeneficiaryDetails getBeneficiaryDetails() {
        return beneficiaryDetails;
    }

    public void setBeneficiaryDetails(BeneficiaryDetails beneficiaryDetails) {
        this.beneficiaryDetails = beneficiaryDetails;
    }

    @Override
    public String toString() {
        return "DebitRequest{" +
                "debitMode='" + debitMode + '\'' +
                ", auth=" + auth +
                ", initiatedBy='" + initiatedBy + '\'' +
                ", feeCharged=" + feeCharged +
                ", beneficiaryDetails=" + beneficiaryDetails +
                '}';
    }
}
