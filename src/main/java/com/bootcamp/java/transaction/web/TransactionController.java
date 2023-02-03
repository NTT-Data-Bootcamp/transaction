package com.bootcamp.java.transaction.web;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.java.transaction.domain.Transaction;
import com.bootcamp.java.transaction.service.TransactionService;
import com.bootcamp.java.transaction.web.mapper.TransactionMapper;
import com.bootcamp.java.transaction.web.model.TransactionModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/client")
public class TransactionController {
	@Value("${spring.application.name}")
	String name;
	
	@Value("${server.port}")
	String port;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionMapper transactionMapper;
	
	
	@Operation(
            summary = "Get a list of transactions",
            description = "Get a list of transactions registered in the system",
            responses = {
            		@ApiResponse(responseCode = "200",
                    description = "The response for the transaction request")
            }
    )
	@GetMapping
	public Mono<ResponseEntity<Flux<TransactionModel>>> getAll(){
		log.info("getAll executed");
		return Mono.just(ResponseEntity.ok()
			.body(transactionService.findAll()
					.map(transaction -> transactionMapper.entityToModel(transaction))));
	}
	
	
	@Operation(summary = "Funcionalidad de consulta de un Transaction por ID")
	@ApiResponses(value= {
		@ApiResponse(responseCode = "200", description = "Transaction found succesully.",
				content = { @Content(mediaType = "application/json",
				schema = @Schema(implementation = Transaction.class)) }),
		@ApiResponse(responseCode = "400", description = "ID not valid.",
			content = @Content),
		@ApiResponse(responseCode = "404", description = "Transaction not found.",
			content = @Content)
	})
	@GetMapping("/{id}")
	public Mono<ResponseEntity<TransactionModel>> getById(@PathVariable String id){
		log.info("getById executed {}", id);
		Mono<Transaction> response = transactionService.findById(id);
		return response
				.map(transaction -> transactionMapper.entityToModel(transaction))
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
				
		
		//        .switchIfEmpty(Mono.error(new DataNotFoundException("The data you seek is not here."))); // NO FUNCIONA
		/*
		 return serverRequest.bodyToMono(RequestDTO.class)
                .map((request) -> searchLocations(request.searchFields, request.pageToken))
                .flatMap( t -> ServerResponse
                        .ok()
                        .body(t, ResponseDTO.class)
                )
                .switchIfEmpty(ServerResponse.notFound().build())
                ;
		 * */
	}
	
	@PostMapping
	public Mono<ResponseEntity<TransactionModel>> create(@Valid @RequestBody TransactionModel request){
		log.info("create executed {}", request);
		return transactionService.create(transactionMapper.modelToEntity(request))
				.map(transaction -> transactionMapper.entityToModel(transaction))
				.flatMap(c ->
					Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
							port, "client", c.getId())))
							.body(c)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<TransactionModel>> updateById(@PathVariable String id, @Valid @RequestBody TransactionModel request){
		log.info("updateById executed {}:{}", id, request);
		return transactionService.update(id, transactionMapper.modelToEntity(request))
				.map(transaction -> transactionMapper.entityToModel(transaction))
				.flatMap(c ->
				Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
						port, "client", c.getId())))
						.body(c)))
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
		log.info("deleteById executed {}", id);
		return transactionService.delete(id)
				.map( r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	
}
