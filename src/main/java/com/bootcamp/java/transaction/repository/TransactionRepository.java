package com.bootcamp.java.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.java.transaction.domain.Transaction;

import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

}
