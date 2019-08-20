package com.ithar.malik.java.spring.application.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document
public class Product {

    @Id
    private ObjectId _id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }
}
