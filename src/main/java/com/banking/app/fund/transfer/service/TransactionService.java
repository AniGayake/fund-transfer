package com.banking.app.fund.transfer.service;

import com.banking.app.fund.transfer.bo.SavingAccount;
import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.CreditRequest;
import com.banking.app.fund.transfer.dto.response.FundCreditResponse;
import com.banking.app.fund.transfer.repository.AccountRepository;
import com.banking.app.fund.transfer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.banking.app.fund.transfer.constants.FundTransferConstants.CREDIT_TRANSACTION_TYPE;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository= accountRepository;
    }

    public Transaction recordTransaction(final CreditRequest creditRequest, final BigDecimal amount, final String accountNumber) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(generateTransactionId());
        transaction.setTransactionAmount(amount);
        transaction.setAccountNumber(accountNumber);
        transaction.setStatus("SUCCESS");
        transaction.setCurrency(creditRequest.getCurrency());
        transaction.setCustomerId(creditRequest.getCustomerId());
        transaction.setDescription(creditRequest.getRemarks());
        transaction.setTransactionType(CREDIT_TRANSACTION_TYPE);
        transaction.setChannel(creditRequest.getChannel());
        transaction.setLocation(creditRequest.getLocation());
        transaction.setMode(creditRequest.getMode());
        transaction.setPurposeCode(creditRequest.getPurposeCode());
        transaction.setReversal(creditRequest.getReversal());
        transaction.setSourceInstitutionCode(creditRequest.getSourceInstitutionCode());
        transaction.setTransactionDate(creditRequest.getTimestamp());
        transaction.setUpdatedAt(LocalDateTime.now());
        transaction.setReversal(creditRequest.getReversal());
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        return transaction;
    }
    private String generateTransactionId() {
        long timestamp= System.currentTimeMillis();
        Random random = new Random();
        int randomInt = random.nextInt(9000)+ 1000;
        return "TXN"+ timestamp+ randomInt;
    }

    public FundCreditResponse populateFundCreditResponse(Transaction transaction) {
        FundCreditResponse fundCreditResponse = new FundCreditResponse();
        fundCreditResponse.setCreditedAmount(transaction.getTransactionAmount());
        fundCreditResponse.setStatus(transaction.getStatus());
        fundCreditResponse.setAccountNumber(transaction.getAccountNumber());
        fundCreditResponse.setCurrency(transaction.getCurrency());
        fundCreditResponse.setMessage(transaction.getDescription());
        fundCreditResponse.setTransactionId(transaction.getTransactionId());
        fundCreditResponse.setTimestamp(transaction.getTransactionDate());
        Optional<BigDecimal> balance= accountRepository.findBalanceByAccountNumber(transaction.getAccountNumber());
        balance.ifPresent(fundCreditResponse::setBalanceAfterCredit);
        return fundCreditResponse;
    }
}
