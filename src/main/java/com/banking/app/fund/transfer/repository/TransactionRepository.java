package com.banking.app.fund.transfer.repository;

import com.banking.app.fund.transfer.bo.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
