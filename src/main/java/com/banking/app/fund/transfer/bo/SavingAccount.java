package com.banking.app.fund.transfer.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class SavingAccount extends Account{

    @Column(name = "interest_rate")
    private Double interestRate;
    @Column(name = "minimum_balance")
    private BigDecimal minimumBalance;
    @Column(name = "withdrawal_limit")
    private Integer withdrawalLimit;

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Integer getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(Integer withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }
}
