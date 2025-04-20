package com.banking.app.fund.transfer.dto.response;

import java.math.BigDecimal;

public class FundDebitResponse extends TransactionResponse{
    private BigDecimal debitedAmount;
    private BigDecimal amountAfterDebit;

    public BigDecimal getDebitedAmount() {
        return debitedAmount;
    }

    public void setDebitedAmount(BigDecimal debitedAmount) {
        this.debitedAmount = debitedAmount;
    }

    public BigDecimal getAmountAfterDebit() {
        return amountAfterDebit;
    }

    public void setAmountAfterDebit(BigDecimal amountAfterDebit) {
        this.amountAfterDebit = amountAfterDebit;
    }

    @Override
    public String toString() {
        return "FundDebitResponse{" +
                "debitedAmount=" + debitedAmount +
                ", amountAfterDebit=" + amountAfterDebit +
                '}';
    }
}
