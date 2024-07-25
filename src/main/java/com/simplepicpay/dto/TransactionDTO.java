package com.simplepicpay.dto;

import com.simplepicpay.domain.user.User;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {

}