package com.simplepicpay.services;

import com.simplepicpay.domain.transaction.Transaction;
import com.simplepicpay.domain.user.User;
import com.simplepicpay.dto.TransactionDTO;
import com.simplepicpay.repositories.TransactionRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate template;

    @Value("${simplepicpay.transaction.authorization.url}")
    private String authorizationUrl;

    @Value("${simplepicpay.transaction.authorization.key}")
    private String authorizationKey;

    @Value("${simplepicpay.transaction.authorized.value}")
    private String authorizadValue;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        if(!authorizeTransaction(sender, transaction.value())) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = Transaction.builder()
                .amount(transaction.value())
                .sender(sender)
                .receiver(receiver)
                .timestamp(LocalDateTime.now())
                .build();

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = template.getForEntity(authorizationUrl, Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String msg = (String) authorizationResponse.getBody().get(authorizationKey);
            return authorizadValue.equalsIgnoreCase(msg);
        }else return false;
    }
}