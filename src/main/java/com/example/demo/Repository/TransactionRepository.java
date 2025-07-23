package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
