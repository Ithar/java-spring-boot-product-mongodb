package com.ithar.malik.java.spring.application.services;

import com.ithar.malik.java.spring.application.domain.Trade;
import com.ithar.malik.java.spring.application.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    private final RiskRewardService riskRewardService;
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(RiskRewardService riskRewardService, TradeRepository tradeRepository) {
        this.riskRewardService = riskRewardService;
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> listAll() {
        List<Trade> trades = new ArrayList<>();
        tradeRepository.findAll().forEach(trades::add);
        return trades;
    }

    public Trade getById(String id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public Trade saveOrUpdate(Trade trade) {
        riskRewardService.calculateRiskReward(trade);
        tradeRepository.save(trade);
        return trade;
    }

    public void delete(String id) {
        tradeRepository.deleteById(id);
    }
}
