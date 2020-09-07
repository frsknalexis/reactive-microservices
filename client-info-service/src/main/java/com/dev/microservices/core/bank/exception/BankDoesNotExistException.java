package com.dev.microservices.core.bank.exception;

public class BankDoesNotExistException extends RuntimeException {

    private String bankId;

    public BankDoesNotExistException(String bankId) {
        this.bankId = bankId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}