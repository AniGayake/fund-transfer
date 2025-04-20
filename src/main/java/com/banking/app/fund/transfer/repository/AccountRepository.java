package com.banking.app.fund.transfer.repository;

import com.banking.app.fund.transfer.bo.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<SavingAccount,String> {
    @Modifying
    @Query(value = "update account set balance = balance + :amount where account_number=:accountNumber",nativeQuery = true)
    int credit(String accountNumber, BigDecimal amount);

    @Query(value = "select account_number from account where customer_id= :customerId",nativeQuery = true)
    List<String> findAllAccountsByCustomerId(Long customerId);

    @Query(value = "select balance from account where account_number = :accountNumber",nativeQuery = true)
    Optional<BigDecimal> findBalanceByAccountNumber(String accountNumber);

    @Modifying
    @Query(value = "update account set balance = balance - :amount where account_number=:accountNumber",nativeQuery = true)
    int debit(String accountNumber,BigDecimal amount);

    @Query(value = "select customer_id from account where account_number = :beneficiaryAccountNumber",nativeQuery = true)
    Long findCustomerIdByAccountNumber(String beneficiaryAccountNumber);
}
