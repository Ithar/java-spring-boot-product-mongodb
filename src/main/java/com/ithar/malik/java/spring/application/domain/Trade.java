package com.ithar.malik.java.spring.application.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Trade {

    @Id
    private ObjectId _id;

    private String currencyPair;

    private String type;

    private Double entryPrice;

    private Double takeProfitPrice;

    private Double stopLossPrice;

    private Double lot;

    private Integer pipRisk;

    private Integer pipReward;

    private Double risk;

    private Double reward;

    private Double commission;

    private Double riskRewardRatio;
}
