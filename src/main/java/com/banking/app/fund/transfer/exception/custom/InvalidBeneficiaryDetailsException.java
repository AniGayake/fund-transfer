package com.banking.app.fund.transfer.exception.custom;

public class InvalidBeneficiaryDetailsException extends RuntimeException{
    public InvalidBeneficiaryDetailsException(String message) {
        super(message);
    }
}
