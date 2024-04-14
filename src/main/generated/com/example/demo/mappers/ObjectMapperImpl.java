package com.example.demo.mappers;

import com.example.demo.domain.AccountLimit;
import com.example.demo.domain.CurrencyValue;
import com.example.demo.domain.Transaction;
import com.example.demo.dto.AccountLimitDto;
import com.example.demo.dto.CurrencyValueDto;
import com.example.demo.dto.TransactionDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-14T18:04:27+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Private Build)"
)
@Component
public class ObjectMapperImpl extends ObjectMapper {

    @Override
    public CurrencyValue currencyValueDtoToCurrencyValue(CurrencyValueDto currencyValueDto) {
        if ( currencyValueDto == null ) {
            return null;
        }

        CurrencyValue currencyValue = new CurrencyValue();

        return currencyValue;
    }

    @Override
    public Transaction transactionDtoToTransaction(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        return transaction;
    }

    @Override
    public TransactionDto transactionToTransactionDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        return transactionDto;
    }

    @Override
    public AccountLimitDto accountLimitToAccountLimitDto(AccountLimit accountLimit) {
        if ( accountLimit == null ) {
            return null;
        }

        AccountLimitDto accountLimitDto = new AccountLimitDto();

        return accountLimitDto;
    }
}
