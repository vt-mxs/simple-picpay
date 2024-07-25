package com.simplepicpay.dto;

import com.simplepicpay.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO (
    String firstName,
    String lastName,
    BigDecimal balance,
    String document,
    String email,
    String password,
    UserType type
) {}