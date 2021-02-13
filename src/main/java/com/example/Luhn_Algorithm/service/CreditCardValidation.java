package com.example.Luhn_Algorithm.service;

public interface CreditCardValidation {
    public boolean isCardValid(String cardNo, String name, String CVV, String expiry_month_year) throws Exception;
}
