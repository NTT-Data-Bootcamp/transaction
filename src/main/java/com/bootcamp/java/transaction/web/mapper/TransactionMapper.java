package com.bootcamp.java.transaction.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bootcamp.java.transaction.domain.Transaction;
import com.bootcamp.java.transaction.web.model.TransactionModel;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

	Transaction modelToEntity(TransactionModel model);

	TransactionModel entityToModel(Transaction event);

	@Mapping(target = "id", ignore = true)
	void update(@MappingTarget Transaction entity, Transaction updateEntity);
}
