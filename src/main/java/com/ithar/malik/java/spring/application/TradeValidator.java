package com.ithar.malik.java.spring.application;

import com.ithar.malik.java.spring.application.domain.Trade;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class TradeValidator {

    public boolean validate(Trade trade, BindingResult result) {

        // check trade type and entries

        return true;
    }

}
