package com.banking.app.fund.transfer.dto.response;

import java.math.BigDecimal;

public class FundTransferResponse extends TransactionResponse{
    private String destinationAccountNumber;
    private BigDecimal transferredAmount;
    private BigDecimal feeCharged;
    private BigDecimal totalDebited;
    private BigDecimal availableBalance;
    private String transferMode;

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public BigDecimal getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(BigDecimal transferredAmount) {
        this.transferredAmount = transferredAmount;
    }

    public BigDecimal getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(BigDecimal feeCharged) {
        this.feeCharged = feeCharged;
    }

    public BigDecimal getTotalDebited() {
        return totalDebited;
    }

    public void setTotalDebited(BigDecimal totalDebited) {
        this.totalDebited = totalDebited;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getTransferMode() {
        return transferMode;
    }

    public void setTransferMode(String transferMode) {
        this.transferMode = transferMode;
    }

    @Override
    public String toString() {
        return "FundTransferResponse{" +
                "destinationAccountNumber='" + destinationAccountNumber + '\'' +
                ", transferredAmount=" + transferredAmount +
                ", feeCharged=" + feeCharged +
                ", totalDebited=" + totalDebited +
                ", availableBalance=" + availableBalance +
                ", transferMode='" + transferMode + '\'' +
                '}';
    }
}
