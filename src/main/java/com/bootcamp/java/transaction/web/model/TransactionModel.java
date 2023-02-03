package com.bootcamp.java.transaction.web.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {

	@JsonIgnore
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
