package com.example.demo.controllers.rest;

import com.example.demo.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Интерфейс контроллера транзакций
 */

@Tag(name = "Транзакции")
@Validated
public interface TransactionController extends BaseController {

    @Operation(summary = "Сохранение и обогащение транзакции в БД")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Запись сохранена")})
    @RequestMapping(
        value = "/createTransaction",
        method = POST,
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<TransactionDto> processTransaction(@RequestBody @Valid final TransactionDto transactionDto);

    @Operation(summary = "Получение списка транзакций превысивших лимит по идентификатору аккаунта")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Записи найдены",
            content = {@Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TransactionDto.class))
            }),
        @ApiResponse(responseCode = "204", description = "Записи не найдены", content = {@Content()})
    })

    @RequestMapping(
        value = "/exceededtransactions/{account}",
        method = GET,
        consumes = ALL_VALUE,
        produces = APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<TransactionDto>> getLimitExceededTransactions(
        @Parameter(name = "account", description = "Идентификатор аккаунта")
        @RequestParam(name = "account", defaultValue = "0")
        @PathVariable @Valid final Long account
    );
}