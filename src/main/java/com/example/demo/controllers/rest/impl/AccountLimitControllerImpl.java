package com.example.demo.controllers.rest.impl;


import com.example.demo.controllers.rest.AccountLimitController;
import com.example.demo.dto.AccountLimitDto;
import com.example.demo.services.AccountLimitService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Реализация контроллера лимитов
 */

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountLimitControllerImpl implements AccountLimitController {

    private final AccountLimitService accountLimitService;

    private HttpSession httpSession;

    @Override
    public ResponseEntity<AccountLimitDto> setNewLimit(AccountLimitDto accountLimitDto) {

        log.info("Trying to update limit with AccountLimitDto: {} with session id: {}", accountLimitDto, httpSession.getId());

        return new ResponseEntity<>(accountLimitService.setNewLimit(accountLimitDto), CREATED);
    }
}