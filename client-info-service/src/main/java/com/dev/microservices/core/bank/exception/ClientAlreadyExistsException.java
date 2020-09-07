package com.dev.microservices.core.bank.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    private String ssn;

    private String bankId;

    public ClientAlreadyExistsException(String ssn, String bankId) {
        this.ssn = ssn;
        this.bankId = bankId;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}