package com.banking.app.fund.transfer.service;

import com.banking.app.fund.transfer.bo.SavingAccount;
import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.CreditRequest;
import com.banking.app.fund.transfer.dto.request.DebitRequest;
import com.banking.app.fund.transfer.dto.response.FundTransferResponse;
import com.banking.app.fund.transfer.dto.response.TransactionResponse;
import com.banking.app.fund.transfer.repository.AccountRepository;
import com.banking.app.fund.transfer.web.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.banking.app.fund.transfer.constants.FundTransferConstants.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    private final DebitService debitService;
    private final CreditService creditService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    @Autowired
    public TransferService(AccountRepository accountRepository,DebitService debitService, CreditService creditService, TransactionService transactionService) {
        this.debitService = debitService;
        this.creditService = creditService;
        this.transactionService = transactionService;
        this.accountRepository= accountRepository;
    }

    public Transaction transferAmount(final DebitRequest debitRequest){
        String accountNumber = debitRequest.getAccountNumber();
        SavingAccount savingAccount = accountRepository.findById(accountNumber).get();
        validateTransferDetails(debitRequest, savingAccount, accountNumber, debitRequest.getAmount());
        LOGGER.info("Debiting the amount from account {}",debitRequest.getAccountNumber());
        Transaction debitTransaction = debitService.debitAccount(debitRequest);
        LOGGER.info("Crediting the amount into account {}",debitRequest.getBeneficiaryDetails().getAccountNumber());
        CreditRequest creditRequest= createCreditRequest(debitRequest);
        Transaction creditTransaction = creditService.creditAccount(creditRequest);
        return debitTransaction;
    }


    private CreditRequest createCreditRequest(final DebitRequest debitRequest) {
        String beneficiaryAccountNumber = debitRequest.getBeneficiaryDetails().getAccountNumber();
        CreditRequest creditRequest = new CreditRequest();
        creditRequest.setAmount(debitRequest.getAmount());
        creditRequest.setTransactionType(CREDIT_TRANSACTION_TYPE);
        creditRequest.setAccountNumber(beneficiaryAccountNumber);
        creditRequest.setChannel(creditRequest.getChannel());
        creditRequest.setCurrency(debitRequest.getCurrency());
        creditRequest.setMode(debitRequest.getDebitMode());
        creditRequest.setPurposeCode(debitRequest.getPurposeCode());
        creditRequest.setCustomerId(accountRepository.findCustomerIdByAccountNumber(beneficiaryAccountNumber));
        creditRequest.setNarration(debitRequest.getNarration());
        creditRequest.setRemarks(debitRequest.getRemarks());
        creditRequest.setTimestamp(LocalDateTime.now());
        creditRequest.setReferenceId(debitRequest.getReferenceId());
        creditRequest.setLocation(debitRequest.getLocation());
        creditRequest.setReversal(debitRequest.getReversal());
        creditRequest.setInitiatedBy(debitRequest.getInitiatedBy());
        creditRequest.setExternalReferenceId(debitRequest.getExternalReferenceId());
        return creditRequest;
    }

    private void validateTransferDetails(DebitRequest debitRequest, SavingAccount savingAccount, String accountNumber, BigDecimal amount) {
        transactionService.checkIfAccountIsActiveAndType(savingAccount);
        transactionService.checkIfAccountBelongsToCustomer(debitRequest.getCustomerId(), accountNumber);
        transactionService.checkIfTransactionAmountIsValid(amount);
        transactionService.checkForBeneficiaryAccountDetails(debitRequest);
    }


}
