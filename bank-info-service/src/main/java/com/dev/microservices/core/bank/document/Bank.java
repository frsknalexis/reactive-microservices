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
@Document(collection = "banks")
public class Bank implements Serializable {

    private static final long serialVersionUID = -6520842283301045477L;

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "country")
    private String country;

    @Field(name = "address")
    private String address;

    @Field(name = "foundation_date")
    private LocalDate foundationDate;
}