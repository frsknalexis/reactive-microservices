package com.dev.microservices.core.bank.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = 2399112128780523971L;

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String ssn;

    private PhoneNumber phoneNumber;

    @Field(name = "birth_date")
    private LocalDate birthDate;

    @Field(name = "registration_date")
    private LocalDate registrationDate;

    @Field(name = "bank_id")
    private String bankId;
}