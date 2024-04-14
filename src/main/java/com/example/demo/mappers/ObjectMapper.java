package com.example.demo.mappers;


import com.example.demo.domain.AccountLimit;
import com.example.demo.domain.Transaction;
import com.example.demo.dto.AccountLimitDto;
import com.example.demo.dto.TransactionDto;
import org.mapstruct.Mapper;

/**
 * Маппер
 */

@Mapper(componentModel = "spring")
public abstract class ObjectMapper {

    public abstract Transaction transactionDtoToTransaction(TransactionDto transactionDto);

    public abstract TransactionDto transactionToTransactionDto(Transaction transaction);

    public abstract AccountLimitDto accountLimitToAccountLimitDto(AccountLimit accountLimit);
}