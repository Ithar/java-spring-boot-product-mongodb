package com.ithar.malik.java.spring.application.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document
@Data
public class Trade {

    @Id
    private ObjectId id;

    private String currencyPair;

    private String type;

    @NotNull(message = "Entry price cannot be empty")
    private Double entryPrice;

    @NotNull(message = "Take profit cannot be empty")
    private Double takeProfitPrice;

    @NotNull(message = "Stop loss cannot be empty")
    private Double stopLossPrice;

    @NotNull(message = "Lot cannot be empty")
    private Double lot;

    private Integer pipRisk;

    private Integer pipReward;

    private Double risk;

    private Double reward;

    private Double commission;

    private Double riskRewardRatio;

    private LocalDateTime createdAt;
}
