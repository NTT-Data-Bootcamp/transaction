
package com.bootcamp.java.transaction.domain;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
//@EqualsAndHashCode(of = { "identityDocumentNumber" })
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "transaction")
public class Transaction {
	@Id
    private String id;
	@NotNull
    private String idClient;
    @NotNull
    private String idProduct;
    
    private String creditNumber;
    private String accountNumberFrom;
    private String cardNumberFrom;
    @NotNull
    
    private String idClientTo; 
    private String productCodeTo;
    private String accountNumberTo;
    private String cardNumberTo;
    @NotNull
    private LocalDate transactionDate;
    @NotNull
    private Float transactionAmount;
    @NotNull
    private Float transactionBalance;
    @NotNull
    private Float transactionCommission;
    @NotNull
    private String transactionType;
    
}
