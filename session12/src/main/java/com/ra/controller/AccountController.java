package com.ra.controller;

import com.ra.model.dto.request.AccountDTO;
import com.ra.model.dto.request.AccountLoginDTO;
import com.ra.model.dto.response.DataResponse;
import com.ra.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<DataResponse<?>> register(@Valid @RequestBody AccountDTO accountDTO) {
        return accountService.register(accountDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse<?>> login(@Valid @RequestBody AccountLoginDTO accountLoginDTO) {
        return accountService.login(accountLoginDTO);
    }
}
