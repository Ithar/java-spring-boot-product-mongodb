package com.ithar.malik.java.spring.application;

import com.ithar.malik.java.spring.application.domain.Trade;
import org.springframework.stereotype.Component;

@Component
public class TradeValidator {

    public boolean validate(Trade trade) {

        return true;
    }

}
