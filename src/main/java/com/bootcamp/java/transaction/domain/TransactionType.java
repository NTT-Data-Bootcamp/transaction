package com.bootcamp.java.transaction.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nonapi.io.github.classgraph.json.Id;

@Data
@Builder
@ToString
@EqualsAndHashCode(of = { "name" })
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "transaction-type")
public class TransactionType {
	@Id
    private String id;
    
	@NotNull
    @Indexed(unique = true)
    private String name;
   
	@NotNull
    private Boolean isOutgoing;
	
    @NotNull
    private Boolean active;
}
