package com.banking.app.fund.transfer.utility;

import com.banking.app.fund.transfer.bo.SavingAccount;
import com.banking.app.fund.transfer.exception.custom.AccountCustomerRelationException;
import com.banking.app.fund.transfer.exception.custom.AccountNotActiveException;
import com.banking.app.fund.transfer.exception.custom.LoanAccountCreditException;
import com.banking.app.fund.transfer.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.banking.app.fund.transfer.constants.FundTransferConstants.ACTIVE_STATUS_CODE;
import static com.banking.app.fund.transfer.constants.FundTransferConstants.LOAN_ACCOUNT_CODE;

public class AccountUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountUtility.class);
    private AccountRepository accountRepository;

    @Autowired
    public AccountUtility(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
}
