package com.dev.microservices.core.bank.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber implements Serializable {

    private static final long serialVersionUID = 6688199918692254312L;

    private String prefix;

    private String countryCode;

    private String number;
}