package com.banking.app.fund.transfer.exception.custom;

public class AccountNotActiveException extends RuntimeException{
    public AccountNotActiveException(String message){
        super(message);
    }
}
