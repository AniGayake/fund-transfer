package com.banking.app.fund.transfer.web;

import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.CreditRequest;
import com.banking.app.fund.transfer.dto.response.FundCreditResponse;
import com.banking.app.fund.transfer.dto.response.TransactionResponse;
import com.banking.app.fund.transfer.exception.custom.FundTransferException;
import com.banking.app.fund.transfer.repository.AccountRepository;
import com.banking.app.fund.transfer.service.CreditService;
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
public class Credit {
    private static final Logger LOGGER= LoggerFactory.getLogger(Credit.class);
    private final CreditService creditService;
    private final TransactionService transactionService;

    public Credit(final CreditService creditService,TransactionService transactionService) {
        this.creditService = creditService;
        this.transactionService = transactionService;
    }

    @PostMapping("/credit")
    public ResponseEntity<TransactionResponse> creditAccount(final @RequestBody CreditRequest creditRequest){
        LOGGER.info("Entering creditAccount() for credit amount for account number {}",creditRequest.getAccountNumber());
        Transaction transaction = creditService.creditAccount(creditRequest);
        if(null==transaction){
            throw new FundTransferException("Error Crediting fund into account");
        }
        TransactionResponse fundCreditResponse = transactionService.populateFundTransferResponse(transaction);
        LOGGER.info("Exiting creditAccount() after Successful Credit Transaction");
        return ResponseEntity.status(HttpStatus.OK).body(fundCreditResponse);
    }
}
