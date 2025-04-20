package com.banking.app.fund.transfer.exception.custom;

public class InvalidDebitAmount extends RuntimeException{
    public InvalidDebitAmount(String message) {
        super(message);
    }
}
