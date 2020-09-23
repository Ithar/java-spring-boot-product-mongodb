package com.ithar.malik.java.spring.application.repositories;

import com.ithar.malik.java.spring.application.domain.Trade;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade, String> {
}
