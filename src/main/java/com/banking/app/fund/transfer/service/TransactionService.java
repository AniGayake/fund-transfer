package com.banking.app.fund.transfer.service;

import com.banking.app.fund.transfer.bo.SavingAccount;
import com.banking.app.fund.transfer.bo.Transaction;
import com.banking.app.fund.transfer.dto.request.CreditRequest;
import com.banking.app.fund.transfer.dto.request.DebitRequest;
import com.banking.app.fund.transfer.dto.request.TransactionRequest;
import com.banking.app.fund.transfer.dto.response.FundCreditResponse;
import com.banking.app.fund.transfer.dto.response.FundDebitResponse;
import com.banking.app.fund.transfer.dto.response.TransactionResponse;
import com.banking.app.fund.transfer.exception.custom.*;
import com.banking.app.fund.transfer.repository.AccountRepository;
import com.banking.app.fund.transfer.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.banking.app.fund.transfer.constants.FundTransferConstants.*;
import static com.banking.app.fund.transfer.constants.FundTransferConstants.LOAN_ACCOUNT_CODE;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository= accountRepository;
    }

    public Transaction recordCreditTransaction(final CreditRequest creditRequest, final BigDecimal amount, final String accountNumber) {
        Transaction transaction = getTransaction(creditRequest);
        transaction.setTransactionType(CREDIT_TRANSACTION_TYPE);
        transaction.setMode(creditRequest.getMode());
        transaction.setSourceInstitutionCode(creditRequest.getSourceInstitutionCode());
        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction recordDebitTransaction(final DebitRequest debitRequest) {
        Transaction transaction = getTransaction(debitRequest);
        transaction.setTransactionType(DEBIT_TRANSACTION_TYPE);
        transaction.setMode(debitRequest.getDebitMode());
        transaction.setBeneficiaryAccountNumber(debitRequest.getBeneficiaryDetails().getAccountNumber());
        transaction.setBeneficiaryAccountName(debitRequest.getBeneficiaryDetails().getName());
        transaction.setBeneficiaryIfscCode(debitRequest.getBeneficiaryDetails().getIfscCode());
        transactionRepository.save(transaction);
        return transaction;
    }
    private Transaction getTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(generateTransactionId());
        transaction.setTransactionAmount(transactionRequest.getAmount());
        transaction.setAccountNumber(transactionRequest.getAccountNumber());
        transaction.setStatus("SUCCESS");
        transaction.setCurrency(transactionRequest.getCurrency());
        transaction.setCustomerId(transactionRequest.getCustomerId());
        transaction.setDescription(transactionRequest.getRemarks());
        transaction.setChannel(transactionRequest.getChannel());
        transaction.setLocation(transactionRequest.getLocation());
        transaction.setPurposeCode(transactionRequest.getPurposeCode());
        transaction.setReversal(transactionRequest.getReversal());
        transaction.setTransactionDate(transactionRequest.getTimestamp());
        transaction.setUpdatedAt(LocalDateTime.now());
        transaction.setCreatedAt(LocalDateTime.now());
        return transaction;
    }
    private String generateTransactionId() {
        long timestamp= System.currentTimeMillis();
        Random random = new Random();
        int randomInt = random.nextInt(9000)+ 1000;
        return "TXN"+ timestamp+ randomInt;
    }

    public TransactionResponse populateFundTransferResponse(Transaction transaction) {
        if(CREDIT_TRANSACTION_TYPE.equals(transaction.getTransactionType())){
            FundCreditResponse fundCreditResponse = getFundCreditResponse(transaction);
            Optional<BigDecimal> balance = getBalanceByAccountNumber(transaction);
            balance.ifPresent(fundCreditResponse::setBalanceAfterCredit);
            return fundCreditResponse;
        }else if(DEBIT_TRANSACTION_TYPE.equals(transaction.getTransactionType())){
            FundDebitResponse fundDebitResponse = getFundDebitResponse(transaction);
            Optional<BigDecimal> updatedBalance = getBalanceByAccountNumber(transaction);
            updatedBalance.ifPresent(fundDebitResponse::setAmountAfterDebit);
           return fundDebitResponse;
        }else if (TRANSFER_TRANSACTION_TYPE.equals(transaction.getTransactionType())){

        }
        return null;
    }

    private FundDebitResponse getFundDebitResponse(Transaction transaction) {
        FundDebitResponse fundDebitResponse  = new FundDebitResponse();
        fundDebitResponse.setDebitedAmount(transaction.getTransactionAmount());
        setCreditDebitTransactionResponse(transaction, fundDebitResponse);
        return fundDebitResponse;
    }
    private FundCreditResponse getFundCreditResponse(Transaction transaction) {
        FundCreditResponse fundCreditResponse = new FundCreditResponse();
        fundCreditResponse.setCreditAmount(transaction.getTransactionAmount());
        setCreditDebitTransactionResponse(transaction,fundCreditResponse);
        return fundCreditResponse;
    }

    private void setCreditDebitTransactionResponse(Transaction transaction, TransactionResponse fundDebitResponse) {
        fundDebitResponse.setAccountNumber(transaction.getAccountNumber());
        fundDebitResponse.setCurrency(transaction.getCurrency());
        fundDebitResponse.setTransactionId(transaction.getTransactionId());
        fundDebitResponse.setStatus(transaction.getStatus());
        fundDebitResponse.setTimestamp(transaction.getTransactionDate());
        fundDebitResponse.setTransactionType(transaction.getTransactionType());
        fundDebitResponse.setMessage(transaction.getDescription());
    }

    private Optional<BigDecimal> getBalanceByAccountNumber(Transaction transaction) {
        return accountRepository.findBalanceByAccountNumber(transaction.getAccountNumber());
    }

    public void checkIfTransactionAmountIsValid(final BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO)<0){
            throw new InvalidDebitAmount("Debit Amount cannot be Negative");
        }
    }
    public void checkIfAccountIsActiveAndType(SavingAccount savingAccount) {
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
    public void checkForBeneficiaryAccountDetails(DebitRequest debitRequest) {
        if(null==debitRequest.getBeneficiaryDetails()){
            throw new InvalidBeneficiaryDetailsException("No Beneficiary Details Provided");
        }
        if(null==debitRequest.getBeneficiaryDetails().getAccountNumber()) {
            throw  new InvalidBeneficiaryDetailsException("Beneficiary Account Number must be provided");
        }
        if (null==debitRequest.getBeneficiaryDetails().getIfscCode()){
            throw  new InvalidBeneficiaryDetailsException("Beneficiary IFSC Code must be provided");
        }
        if (null==debitRequest.getBeneficiaryDetails().getName()){
            throw  new InvalidBeneficiaryDetailsException("Beneficiary Name must be provided");
        }
    }
    public BigDecimal getFinalDebitAmountAfterFeedAddition(DebitRequest debitRequest) {
        BigDecimal amount= debitRequest.getAmount();
        if(IMPS.equals(debitRequest.getDebitMode())){
            amount= amount.add(new BigDecimal("5.00"));
        } else if (RTGS.equals(debitRequest.getDebitMode())) {
            amount = amount.add(new BigDecimal("10.00"));
        }
        debitRequest.setAmount(amount);
        return amount;
    }

}
