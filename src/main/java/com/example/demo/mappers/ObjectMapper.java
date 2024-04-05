package com.example.demo.mappers;


import com.example.demo.domain.AccountLimits;
import com.example.demo.domain.CurrencyValue;
import com.example.demo.domain.Transaction;
import com.example.demo.dto.AccountLimitsDto;
import com.example.demo.dto.CurrencyValueDto;
import com.example.demo.dto.TransactionDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * Маппер
 */

@Mapper(componentModel = "spring")
public abstract class ObjectMapper {

	public abstract CurrencyValue currencyValueDtoToCurrencyValue(CurrencyValueDto currencyValueDto);

	public abstract CurrencyValueDto currencyValueToCurrencyValueDto(CurrencyValue currencyValue);

	@BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
	public abstract void updateCurrencyValueFromCurrencyValueDto(CurrencyValueDto currencyValueDto,
																 @MappingTarget CurrencyValue currencyValue);

	public abstract Transaction transactionDtoToTransaction(TransactionDto transactionDto);

	public abstract TransactionDto transactionToTransactionDto(Transaction transaction);


	public abstract AccountLimits accountLimitsDtoToAccountLimits(AccountLimitsDto accountLimitsDto);

	public abstract AccountLimitsDto accountLimitsToAccountLimitsDto(AccountLimits accountLimits);
}