package com.banking.app.fund.transfer.service;

import com.banking.app.fund.transfer.bo.SavingAccount;
import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.DebitRequest;
import com.banking.app.fund.transfer.exception.custom.InsufficientBalanceException;
import com.banking.app.fund.transfer.exception.custom.InvalidDebitAmount;
import com.banking.app.fund.transfer.repository.AccountRepository;
import com.banking.app.fund.transfer.utility.AccountUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DebitService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebitService.class);
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Autowired
    public DebitService(AccountRepository accountRepository,TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService= transactionService;
    }

    @Transactional
    public Transaction debitAccount(final DebitRequest debitRequest){
        LOGGER.info("Entering debitAccount() for debit transaction");
        String accountNumber= debitRequest.getAccountNumber();
        BigDecimal debitRequestAmount = debitRequest.getAmount();
        AtomicInteger affectedRows = new AtomicInteger();
        AccountUtility accountUtility = new AccountUtility(accountRepository);
        LOGGER.info("Checking if Debit amount is valid...");
        transactionService.checkIfTransactionAmountIsValid(debitRequestAmount);
        LOGGER.info("Checking if Account belongs to customer");
        accountUtility.checkIfAccountBelongsToCustomer(debitRequest.getCustomerId(),accountNumber);

        Optional<SavingAccount> savingAccount = getSavingAccount(accountNumber);
        LOGGER.info("Checking if Account is not LOAN account");
        savingAccount.ifPresent(accountUtility::checkIfAccountIsActiveAndType);
        LOGGER.info("Initiating Fund Debit Request for account {}",accountNumber);

            if(checkSufficientBalance(accountNumber,debitRequestAmount)){
                LOGGER.info("Sufficient Balance, Proceeding the Debit Transaction");
                affectedRows.set(accountRepository.debit(accountNumber, debitRequestAmount));
            }else {
                LOGGER.error("Insufficient Balance, aborting Debit Transaction");
                throw new InsufficientBalanceException("Insufficient Balance, aborting Debit Transaction");
            }

        if(affectedRows.get() ==1){
            LOGGER.info("Debit transaction Successful");
            return transactionService.recordDebitTransaction(debitRequest);
        }
            return null;
    }

    private Optional<SavingAccount> getSavingAccount(String accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    private boolean checkSufficientBalance(final String accountNumber,final BigDecimal amount){
        Optional<BigDecimal> balance = accountRepository.findBalanceByAccountNumber(accountNumber);
        BigDecimal availableBalance = balance.get().setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal debitAmount = amount.setScale(2,RoundingMode.HALF_EVEN);
        return availableBalance.compareTo(debitAmount)>=0;
    }
}
