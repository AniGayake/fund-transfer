package com.banking.app.fund.transfer.web;

import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.DebitRequest;
import com.banking.app.fund.transfer.dto.response.TransactionResponse;
import com.banking.app.fund.transfer.exception.custom.FundTransferException;
import com.banking.app.fund.transfer.service.DebitService;
import com.banking.app.fund.transfer.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funds")
public class Debit {
    private static final Logger LOGGER = LoggerFactory.getLogger(Debit.class);
    private final DebitService debitService;
    private final TransactionService transactionService;

    @Autowired
    public Debit(DebitService debitService, TransactionService transactionService) {
        this.debitService = debitService;
        this.transactionService = transactionService;
    }

    @PostMapping("/debit")
    public ResponseEntity<TransactionResponse> debitAmountFromAccount(final @RequestBody DebitRequest debitRequest){
        LOGGER.info("Entering debitAmountFromAccount() ");
        Transaction transaction = debitService.debitAccount(debitRequest);
        if(null==transaction){
            throw new FundTransferException("Error Debiting funds from account");
        }
        TransactionResponse fundDebitResponse = transactionService.populateFundTransferResponse(transaction);
        LOGGER.info("Exiting debitAmountFromAccount() after Debit transaction completed");
        return ResponseEntity.status(HttpStatus.CREATED).body(fundDebitResponse);
    }
}
