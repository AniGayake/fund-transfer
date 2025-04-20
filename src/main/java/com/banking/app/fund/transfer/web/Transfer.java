package com.banking.app.fund.transfer.web;

import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.DebitRequest;
import com.banking.app.fund.transfer.dto.response.FundDebitResponse;
import com.banking.app.fund.transfer.dto.response.FundTransferResponse;
import com.banking.app.fund.transfer.dto.response.TransactionResponse;
import com.banking.app.fund.transfer.exception.custom.InvalidBeneficiaryDetailsException;
import com.banking.app.fund.transfer.service.CreditService;
import com.banking.app.fund.transfer.service.DebitService;
import com.banking.app.fund.transfer.service.TransactionService;
import com.banking.app.fund.transfer.service.TransferService;
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
public class Transfer {
    private final static Logger LOGGER = LoggerFactory.getLogger(Transfer.class);

    private final TransferService transferService;
    private final TransactionService transactionService;

    @Autowired
    public Transfer(TransferService transferService,TransactionService transactionService) {
        this.transferService = transferService;
        this.transactionService=transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(final @RequestBody DebitRequest debitRequest){
        LOGGER.info("Entering transfer in controller");
        Transaction transaction = transferService.transferAmount(debitRequest);
        TransactionResponse transferResponse = transactionService.populateFundTransferResponse(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferResponse);
    }


}
