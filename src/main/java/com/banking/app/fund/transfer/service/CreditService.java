package com.banking.app.fund.transfer.service;

import com.banking.app.fund.transfer.bo.SavingAccount;
import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.CreditRequest;
import com.banking.app.fund.transfer.dto.response.FundCreditResponse;
import com.banking.app.fund.transfer.exception.custom.AccountCustomerRelationException;
import com.banking.app.fund.transfer.exception.custom.AccountNotActiveException;
import com.banking.app.fund.transfer.exception.custom.AccountNotFoundException;
import com.banking.app.fund.transfer.exception.custom.LoanAccountCreditException;
import com.banking.app.fund.transfer.repository.AccountRepository;
import com.banking.app.fund.transfer.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.banking.app.fund.transfer.constants.FundTransferConstants.*;

@Service
public class CreditService {
    private static final Logger LOGGER= LoggerFactory.getLogger(CreditService.class);
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    @Autowired
    public CreditService(AccountRepository accountRepository ,TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Transactional
    public Transaction creditAccount(final CreditRequest creditRequest){
       final String accountNumber =  creditRequest.getAccountNumber();
       final BigDecimal amount = creditRequest.getAmount();

       LOGGER.info("Getting account details for account number {}",accountNumber);
       Optional<SavingAccount> savingAccount =accountRepository.findById(accountNumber);

       savingAccount.orElseThrow(()->{
           LOGGER.error("Account number "+ accountNumber+ " not found");
           throw new AccountNotFoundException("Account number "+ accountNumber+ " not found");
       });
       checkIfAccountBelongsToCustomer(creditRequest.getCustomerId(),accountNumber);
       checkIfAccountIsActiveAndType(savingAccount.get());

       int affectedRows = accountRepository.credit(accountNumber,amount);
       if(affectedRows==1){
           Transaction transaction = transactionService.recordTransaction(creditRequest,amount,accountNumber);
           LOGGER.info("Amount {} credited Successfully into account {} ",amount,accountNumber);
           return  transaction;
       }
       return null;
    }


    private void checkIfAccountIsActiveAndType(SavingAccount savingAccount) {
        String accountType = savingAccount.getAccountTypeCode();
        if(!ACTIVE_STATUS_CODE.equals(savingAccount.getStatusCode())){
            throw new AccountNotActiveException("Account number "+ savingAccount.getAccountNumber()+ " is not Active");
        }
        if(LOAN_ACCOUNT_CODE.equals(accountType)){
            throw new LoanAccountCreditException("Cannot credit into loan account");
        }
    }
    public void checkIfAccountBelongsToCustomer(final Long customerId,final String accountNumber){
        List<String> accountNumbers = accountRepository.findAllAccountsByCustomerId(customerId);
        if(!accountNumbers.contains(accountNumber)){
            throw new AccountCustomerRelationException("Given account number "+ accountNumber+ " does not belong to the provided customerId "+ customerId);
        }
    }

}
