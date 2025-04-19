package com.banking.app.fund.transfer.exception.custom;

public class LoanAccountCreditException extends RuntimeException{
    public LoanAccountCreditException(String message){
        super(message);
    }
}
