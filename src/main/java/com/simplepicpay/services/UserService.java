package com.simplepicpay.services;
import com.simplepicpay.domain.user.User;
import com.simplepicpay.domain.user.UserType;
import com.simplepicpay.dto.UserDTO;
import com.simplepicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo logista não esta autorizado a fazer transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw  new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        repository.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public void saveUser(User user){
        repository.save(user);
    }
}