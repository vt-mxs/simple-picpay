package com.simplepicpay.controllers;
import com.simplepicpay.domain.transaction.Transaction;
import com.simplepicpay.domain.user.User;
import com.simplepicpay.dto.TransactionDTO;
import com.simplepicpay.dto.UserDTO;
import com.simplepicpay.services.TransactionService;
import com.simplepicpay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = service.createTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}