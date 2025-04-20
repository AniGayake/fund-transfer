package com.banking.app.fund.transfer.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class FundCreditResponse extends TransactionResponse{
    private BigDecimal balanceAfterCredit;
    private BigDecimal creditAmount;

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getBalanceAfterCredit() {
        return balanceAfterCredit;
    }

    public void setBalanceAfterCredit(BigDecimal balanceAfterCredit) {
        this.balanceAfterCredit = balanceAfterCredit;
    }


    @Override
    public String toString() {
        return "FundCreditResponse{" +
                "balanceAfterCredit=" + balanceAfterCredit +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
