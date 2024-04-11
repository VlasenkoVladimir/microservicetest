package com.example.demo.controllers.rest;

import com.example.demo.dto.AccountLimitDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Интерфейс контроллера лимитов
 */

@Tag(name = "Лимиты на операции")
@Validated
public interface AccountLimitController extends BaseController {

    @Operation(summary = "Сохранение нового лимита в БД")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Запись сохранена")})
    @RequestMapping(value = "/newlimit", method = POST, consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<AccountLimitDto> setNewLimit(@RequestBody @Valid final AccountLimitDto accountLimitDto);
}