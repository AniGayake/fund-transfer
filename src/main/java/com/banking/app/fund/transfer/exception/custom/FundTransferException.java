package com.banking.app.fund.transfer.exception.custom;

import com.banking.app.fund.transfer.dto.response.FundCreditResponse;

public class FundTransferException extends RuntimeException{
    public FundTransferException(String message){
        super(message);
    }
}
