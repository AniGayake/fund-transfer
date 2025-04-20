package com.banking.app.fund.transfer.exception;


import com.banking.app.fund.transfer.exception.custom.*;
import com.banking.app.fund.transfer.exception.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final AccountNotFoundException accountNotFoundException){
        ErrorMessage errorMessage = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), accountNotFoundException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(LoanAccountCreditException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final LoanAccountCreditException loanAccountCreditException){
        ErrorMessage errorMessage = new ErrorMessage("Transaction Not Allowded", HttpStatus.BAD_REQUEST.value(), loanAccountCreditException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotActiveException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final AccountNotActiveException accountNotActiveException){
        ErrorMessage errorMessage = new ErrorMessage("ACCOUNT NOT ACTIVE", HttpStatus.BAD_REQUEST.value(), accountNotActiveException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FundTransferException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final FundTransferException fundTransferException){
        ErrorMessage errorMessage = new ErrorMessage("ACCOUNT NOT ACTIVE", HttpStatus.INTERNAL_SERVER_ERROR.value(), fundTransferException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountCustomerRelationException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final AccountCustomerRelationException accountCustomerRelationException){
        ErrorMessage errorMessage = new ErrorMessage("Account Customer Relation Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), accountCustomerRelationException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final InsufficientBalanceException insufficientBalanceException){
        ErrorMessage errorMessage = new ErrorMessage("LOW BALANCE", HttpStatus.CONFLICT.value(), insufficientBalanceException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidDebitAmount.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final InvalidDebitAmount invalidDebitAmount){
        ErrorMessage errorMessage = new ErrorMessage("INVALID DEBIT AMT", HttpStatus.CONFLICT.value(), invalidDebitAmount.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidBeneficiaryDetailsException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(final InvalidBeneficiaryDetailsException invalidBeneficiaryDetailsException){
        ErrorMessage errorMessage = new ErrorMessage("INVALID BENEFICIARY", HttpStatus.CONFLICT.value(), invalidBeneficiaryDetailsException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }
}
